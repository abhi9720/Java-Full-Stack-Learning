package com.nagarro.miniassignment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nagarro.miniassignment.dto.UserListResponseDTO;
import com.nagarro.miniassignment.model.User;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

	
	@Autowired
	UserRepository userRepository;



	@Test
	public void UserRepository_SaveAll_ReturnResult() {

		User user1 =  User.builder().name("Abhishek").age(22)
				.dateCreated(new Date())
				.dob(new Date()).gender("Male").nationality("NZ").verificationStatus("VERIFIED").build();		

		User user2 =  User.builder().name("Anamika").age(21)
				.dateCreated(new Date())
				.dob(new Date()).gender("Female").nationality("IN").verificationStatus("TO_BE_VERIFIED").build();	

		userRepository.save(user1);
		userRepository.save(user2);		


		List<User>userlist =  userRepository.findAll();

		Assertions.assertThat(userlist.size()).isNotNull();
		Assertions.assertThat(userlist.size()).isEqualTo(2);


	}


	@Test
	public void testFindUsersWithOffsetAndLimit() {
		User user1 =  User.builder().name("Abhishek").age(22)
				.dateCreated(new Date())
				.dob(new Date()).gender("Male").nationality("NZ").verificationStatus("VERIFIED").build();		

		User user2 =  User.builder().name("Anamika").age(21)
				.dateCreated(new Date())
				.dob(new Date()).gender("Female").nationality("IN").verificationStatus("TO_BE_VERIFIED").build();	

		userRepository.save(user1);
		userRepository.save(user2);		

		// Test the method
		UserListResponseDTO userListResponseDTO = userRepository.findUsersWithOffsetAndLimit(10, 2);


		System.out.println(userListResponseDTO.getUsers().size());
		// Assertions
		assertNotNull(userListResponseDTO);
		assertEquals(0, userListResponseDTO.getUsers().size());
		assertFalse(userListResponseDTO.getPageInfo().isHasNextPage());
		assertTrue(userListResponseDTO.getPageInfo().isHasPreviousPage());
		assertEquals(2, userListResponseDTO.getPageInfo().getTotal());



		userListResponseDTO = userRepository.findUsersWithOffsetAndLimit(0, 2);


		assertNotNull(userListResponseDTO);
		assertEquals(2, userListResponseDTO.getUsers().size());
		assertFalse(userListResponseDTO.getPageInfo().isHasNextPage());
		assertFalse(userListResponseDTO.getPageInfo().isHasPreviousPage());
		assertEquals(2, userListResponseDTO.getPageInfo().getTotal());


		userListResponseDTO = userRepository.findUsersWithOffsetAndLimit(0, 1);


		assertNotNull(userListResponseDTO);
		assertEquals(1, userListResponseDTO.getUsers().size());
		assertTrue(userListResponseDTO.getPageInfo().isHasNextPage());
		assertFalse(userListResponseDTO.getPageInfo().isHasPreviousPage());
		assertEquals(2, userListResponseDTO.getPageInfo().getTotal());

	}
}
