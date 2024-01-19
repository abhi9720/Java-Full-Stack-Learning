package com.nagarro.miniassignment.repository;


import com.nagarro.miniassignment.dto.UserListResponseDTO;

public interface UserRepositoryCustom {
    UserListResponseDTO findUsersWithOffsetAndLimit(int offset, int limit);
}
