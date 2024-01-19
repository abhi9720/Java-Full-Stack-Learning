package com.nagarro.miniassignment.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.nagarro.miniassignment.dto.GenderResponseDTO;
import com.nagarro.miniassignment.dto.NationalizeResponseDTO;
import com.nagarro.miniassignment.dto.RandomUserDTO;
import com.nagarro.miniassignment.dto.UserDTO;
import com.nagarro.miniassignment.dto.UserListResponseDTO;
import com.nagarro.miniassignment.model.User;
import com.nagarro.miniassignment.repository.UserRepository;
import com.nagarro.miniassignment.sorting.UserSortService;
import com.nagarro.miniassignment.util.WebClientUtils;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    
	@Autowired
	private UserRepository userRepository;

	 private final WebClient randomUserWebClient;
	    private final WebClient nationalizeApiWebClient;
	    private final WebClient genderizeApiWebClient;
	    private final ExecutorService executorService;

	    @Autowired
	    public UserServiceImpl(@Qualifier("randomUserApiWebClient") WebClient randomUserWebClient,
	                           @Qualifier("nationalityApiWebClient") WebClient nationalizeApiWebClient,
	                           @Qualifier("genderApiWebClient") WebClient genderizeApiWebClient) {
	        this.randomUserWebClient = randomUserWebClient;
	        this.nationalizeApiWebClient = nationalizeApiWebClient;
	        this.genderizeApiWebClient = genderizeApiWebClient;
	        this.executorService = Executors.newFixedThreadPool(10);
	        
	        System.out.println(randomUserWebClient.get());
	    }
	    

	@Override
	public List<User> createUsers(int size) {
		System.out.println("Request for " + size + " Random User");
		List<User> users =  fetchAndVerifyRandomUser(size).collectList().block();
		return users;
	}

	@Override
	public UserListResponseDTO getUsers(String sortType, String sortOrder, int limit, int offset) {
	    
		UserListResponseDTO userListResponseDTO = userRepository.findUsersWithOffsetAndLimit(offset, limit);		
		
		List<User> users = UserSortService.sortUsers(userListResponseDTO.getUsers(), sortType, sortOrder);

		userListResponseDTO.setUsers(users);;
	    
	   return userListResponseDTO;
	}

	
	
	private Flux<User> fetchAndVerifyRandomUser(int size) {


		 return getRandomUsers(size)
		            .flatMap(randomUser -> verifyUserGenderAndNationality(randomUser)
		                    .map(verificationResult -> saveUser(randomUser, verificationResult))
		                    .onErrorResume(e -> {
		                        log.warn("Error processing user verification: {}", e.getMessage());
		                        return Mono.error(new Exception("Error processing user verification"));
		                    }));
			
	}	

	private User saveUser(UserDTO randomUser, String verificationResult) {

		User user = new User();
		user.setAge(randomUser.getDob().getAge());
		user.setDob(randomUser.getDob().getDate());
		user.setName(randomUser.getFullName());
		user.setGender(randomUser.getGender());
		user.setNationality(randomUser.getNationality());
		user.setVerificationStatus(verificationResult);

		return userRepository.save(user);
	}

	public static String verifyUser(UserDTO userDTO, NationalizeResponseDTO nationalizeResponseDTO,
			GenderResponseDTO genderizeResponseDTO) {

		String userNationality = userDTO.getNationality();
		String userGender = userDTO.getGender();

		// Check if the user's nationality matches
		List<NationalizeResponseDTO.CountryProbability> countries = nationalizeResponseDTO.getCountry();
		boolean isNationalityMatch = countries.stream()
				.anyMatch(country -> userNationality.equals(country.getCountry_id()));

		// Check if the user's gender matches
		boolean isGenderMatch = userGender.equals(genderizeResponseDTO.getGender());

		if (isNationalityMatch && isGenderMatch) {
			return "VERIFIED";
		} else {
			return "TO_BE_VERIFIED";
		}
	}

	private Flux<UserDTO> getRandomUsers(int size) {
		
		System.out.println(randomUserWebClient);
		
		 return randomUserWebClient.get()
		            .uri("/?results={size}", size)
		            .retrieve()
		            .bodyToMono(RandomUserDTO.class)
		            .flatMapMany(response -> {
		                if (response != null && response.getResults() != null) {
		                    return Flux.fromIterable(response.getResults());
		                } else {
		                    log.warn("Received empty or null response from randomUserWebClient");
		                    return Flux.empty();
		                }
		            })
		            .onErrorResume(WebClientResponseException.class, error ->
		                    WebClientUtils.handleWebClientError(error, "", ""));
	}

	private Mono<String> verifyUserGenderAndNationality(UserDTO userDTO) {
	    String name = userDTO.getName().getFirst();

	    CompletableFuture<NationalizeResponseDTO> nationalityFuture = CompletableFuture.supplyAsync(
	            () -> getNationalityResponse(name).block(), executorService);

	    CompletableFuture<GenderResponseDTO> genderFuture = CompletableFuture.supplyAsync(
	            () -> getGenderResponse(name).block(), executorService);

	    return Mono.fromFuture(() -> CompletableFuture.allOf(nationalityFuture, genderFuture)
	            .thenApply(ignored -> verifyUser(userDTO, nationalityFuture.join(), genderFuture.join())));
	}


	private Mono<NationalizeResponseDTO> getNationalityResponse(String name) {
		return nationalizeApiWebClient.get().uri("/?name={name}", name).retrieve()
				.bodyToMono(NationalizeResponseDTO.class)
				.doOnNext(result -> log.info("Nationality API call completed on thread {}",
						Thread.currentThread().getId()))
				.onErrorResume(WebClientResponseException.class,
						error -> WebClientUtils.handleWebClientError(error, "", ""));
	}

	private Mono<GenderResponseDTO> getGenderResponse(String name) {
		return genderizeApiWebClient.get().uri("/?name={name}", name).retrieve().bodyToMono(GenderResponseDTO.class)
				.doOnNext(result -> log.info("Gender API call completed on thread {}", Thread.currentThread().getId()))
				.onErrorResume(WebClientResponseException.class,
						error -> WebClientUtils.handleWebClientError(error, "", ""));
	}

	@PreDestroy
	public void shutDownExecutorService() {
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

}