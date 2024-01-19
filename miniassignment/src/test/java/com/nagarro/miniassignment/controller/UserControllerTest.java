package com.nagarro.miniassignment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.nagarro.miniassignment.config.GlobalExceptionHandler;
import com.nagarro.miniassignment.dto.UserListResponseDTO;
import com.nagarro.miniassignment.exception.ValidationException;
import com.nagarro.miniassignment.model.User;
import com.nagarro.miniassignment.service.UserService;

@SpringBootTest
public class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Autowired
	private ObjectMapper objectMapper;

	private List<User> userList;

	private UserListResponseDTO userListResponseDTO;

	@BeforeEach
	public void init() {

		mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new GlobalExceptionHandler())
				.build();

		User user1 = User.builder().userId(1L).name("John Doe").age(25).gender("Male").dob(new Date()).nationality("US")
				.verificationStatus("VERIFIED").dateCreated(new Date()).dateModified(new Date()).build();

		User user2 = User.builder().userId(2L).name("Jane Doe").age(30).gender("Female").dob(new Date())
				.nationality("CA").verificationStatus("VERIFIED").dateCreated(new Date()).dateModified(new Date())
				.build();

		User user3 = User.builder().userId(2L).name("Ammy").age(30).gender("Female").dob(new Date()).nationality("CA")
				.verificationStatus("TO_BE_VERIFIED").dateCreated(new Date()).dateModified(new Date()).build();

		this.userList = Arrays.asList(user1, user2, user3);

		UserListResponseDTO.PageInfo pageInfo = UserListResponseDTO.PageInfo.builder().hasPreviousPage(false)
				.hasNextPage(true).total(2L).build();

		this.userListResponseDTO = UserListResponseDTO.builder().users(userList).pageInfo(pageInfo).build();
	}

	@Test
	public void UserController_CreateUser_ReturnCreated() throws Exception {
		given(userService.createUsers(2)).willAnswer((invocation -> userList.subList(0, 2)));

		given(userService.createUsers(2)).willReturn(userList.subList(0, 2));

		Map<String, Object> payload = new HashMap<>();
		payload.put("size", "2");

		MvcResult mvcResult = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(payload))).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
//
//        ResponseEntity<?> userListResponse = userController.createUsers(payload);
//        assertEquals(HttpStatus.OK, userListResponse.getStatusCode());             

	}

	@Test
	public void UserController_CreateUser_InvalidPayloadSize() {
		Map<String, Object> payload = new HashMap<>();
		payload.put("size", "abc");

		given(userService.createUsers(anyInt()))
				.willThrow(new ValidationException("Invalid 'size' parameter. It must be a valid integer."));

		ValidationException exception = assertThrows(ValidationException.class,
				() -> userController.createUsers(payload));

		assertThat(exception.getMessage()).isEqualTo("Invalid 'size' parameter. It must be a valid integer.");

		verify(userService, never()).createUsers(anyInt());
	}

	@Test
	public void UserController_GetUsers_ReturnUsers() throws Exception {
		String sortType = "name";
		String sortOrder = "even";
		String limitParam = "5";
		String offsetParam = "0";

		when(userService.getUsers(sortType, sortOrder, 5, 0)).thenReturn(userListResponseDTO);

		ResultActions response = mockMvc.perform(get("/users").param("sortType", sortType).param("sortOrder", sortOrder)
				.param("limit", limitParam).param("offset", offsetParam).contentType(MediaType.APPLICATION_JSON));

		response.andExpect(status().isOk());

		
		String expectedJson = objectMapper.writeValueAsString(userListResponseDTO.getUsers());
		assertThat(expectedJson.length()).isGreaterThan(0);

		String responseBody = response.andReturn().getResponse().getContentAsString();

		boolean hasNextPage = JsonPath.read(responseBody, "$.pageInfo.hasNextPage");
		int total = JsonPath.read(responseBody, "$.pageInfo.total");

		assertThat(hasNextPage).isTrue();
		assertThat(total).isEqualTo(2);

		verify(userService, times(1)).getUsers(sortType, sortOrder, 5, 0);
	}

	@Test
	public void UserController_GetUsers_InvalidSortType() throws Exception {
		// Invalid sortType
		String invalidSortType = "invalidSortType";
		String sortOrder = "even";
		String limitParam = "5";
		String offsetParam = "0";

		ResultActions response = mockMvc.perform(get("/users").param("sortType", invalidSortType)
				.param("sortOrder", sortOrder).param("limit", limitParam).param("offset", offsetParam)
				.contentType(MediaType.APPLICATION_JSON));

		response.andExpect(status().isBadRequest());
		verify(userService, never()).getUsers(anyString(), anyString(), anyInt(), anyInt());

	}

	@Test
	public void UserController_GetUsers_InvalidSortType2() throws Exception {
		String invalidSortType = "name";
		String sortOrder = "even";
		String limitParam = "15";
		String offsetParam = "0";

		ResultActions response = mockMvc.perform(get("/users").param("sortType", invalidSortType)
				.param("sortOrder", sortOrder).param("limit", limitParam).param("offset", offsetParam)
				.contentType(MediaType.APPLICATION_JSON));

		response.andExpect(status().isBadRequest());
		verify(userService, never()).getUsers(anyString(), anyString(), anyInt(), anyInt());

	}

	@Test
	public void UserController_GetUsers_MissingParams() throws Exception {
		String invalidSortType = "name";
		String sortOrder = "even";
		String limitParam = "15";

		ResultActions response = mockMvc.perform(get("/users").param("sortType", invalidSortType)
				.param("sortOrder", sortOrder).param("limit", limitParam).contentType(MediaType.APPLICATION_JSON));

		response.andExpect(status().isBadRequest());
		verify(userService, never()).getUsers(anyString(), anyString(), anyInt(), anyInt());

	}

}