package com.nagarro.miniassignment.service;

import java.util.List;


import com.nagarro.miniassignment.dto.UserListResponseDTO;
import com.nagarro.miniassignment.model.User;

public interface UserService {

	public List<User> createUsers(int	size);
	
	public UserListResponseDTO getUsers(String sortType, String sortOrder, int limit, int offset);
}
