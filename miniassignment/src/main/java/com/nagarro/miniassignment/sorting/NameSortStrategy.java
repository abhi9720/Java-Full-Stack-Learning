package com.nagarro.miniassignment.sorting;

import java.util.List;
import java.util.stream.Collectors;

import com.nagarro.miniassignment.model.User;

public class NameSortStrategy implements UserSortStrategy {
	 private String sortOrder;

	    public NameSortStrategy(String sortOrder) {
	        this.sortOrder = sortOrder;
	    }

	    @Override
	    public List<User> sort(List<User> users) {
	        return users.stream()
	                .sorted((user1, user2) -> {
	                    if (("even".equalsIgnoreCase(sortOrder) && user1.getName().length() % 2 == 0) ||
	                        ("odd".equalsIgnoreCase(sortOrder) && user1.getName().length() % 2 != 0)) {
	                        return -1; // user1 comes first
	                    } else if (("even".equalsIgnoreCase(sortOrder) && user2.getName().length() % 2 == 0) ||
	                               ("odd".equalsIgnoreCase(sortOrder) && user2.getName().length() % 2 != 0)) {
	                        return 1; // user2 comes first
	                    } else {
	                        return user1.getName().compareTo(user2.getName());
	                    }
	                })
	                .collect(Collectors.toList());
	    }
}
