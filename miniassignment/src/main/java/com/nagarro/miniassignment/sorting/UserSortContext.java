package com.nagarro.miniassignment.sorting;

import java.util.List;

import com.nagarro.miniassignment.model.User;

public class UserSortContext {
    private UserSortStrategy sortStrategy;

    public UserSortContext(UserSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public List<User> sortUsers(List<User> users) {
        return sortStrategy.sort(users);
    }

	public UserSortStrategy getSortStrategy() {
		return sortStrategy;
	}

	public void setSortStrategy(UserSortStrategy sortStrategy) {
		this.sortStrategy = sortStrategy;
	}
    
}
