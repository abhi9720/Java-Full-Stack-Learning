package com.nagarro.miniassignment.sorting;

import java.util.List;


import com.nagarro.miniassignment.model.User;


public class UserSortService {


	
	  public static List<User> sortUsers(List<User> users, String sortType, String sortOrder) {
	        UserSortStrategy strategy;

	        if ("Age".equalsIgnoreCase(sortType)) {
	            strategy = new AgeSortStrategy(sortOrder);
	        } else if ("Name".equalsIgnoreCase(sortType)) {
	            strategy = new NameSortStrategy(sortOrder);
	        } else {
	            throw new IllegalArgumentException("Invlaid Argument");
	            
	        }

	        UserSortContext userSortContext=  new UserSortContext(strategy);

	        return userSortContext.sortUsers(users);
	    }
}
