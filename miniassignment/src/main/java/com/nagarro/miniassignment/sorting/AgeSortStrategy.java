package com.nagarro.miniassignment.sorting;

import java.util.List;
import java.util.stream.Collectors;

import com.nagarro.miniassignment.model.User;

public class AgeSortStrategy implements UserSortStrategy {
    
    private String sortOrder;

    public AgeSortStrategy(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public List<User> sort(List<User> users) {
        return users.stream()
                .sorted((user1, user2) -> {
                    if (("even".equalsIgnoreCase(sortOrder) && user1.getAge() % 2 == 0) ||
                        ("odd".equalsIgnoreCase(sortOrder) && user1.getAge() % 2 != 0)) {
                        return -1; // user1 comes first
                    } else if (("even".equalsIgnoreCase(sortOrder) && user2.getAge() % 2 == 0) ||
                               ("odd".equalsIgnoreCase(sortOrder) && user2.getAge() % 2 != 0)) {
                        return 1; // user2 comes first
                    } else {
                        return Integer.compare(user1.getAge(), user2.getAge());
                    }
                })
                .collect(Collectors.toList());
    }
}

