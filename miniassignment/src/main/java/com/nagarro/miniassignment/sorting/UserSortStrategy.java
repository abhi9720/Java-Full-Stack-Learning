package com.nagarro.miniassignment.sorting;

import java.util.List;

import com.nagarro.miniassignment.model.User;

public interface UserSortStrategy {
    List<User> sort(List<User> users);
}
