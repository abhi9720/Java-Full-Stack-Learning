package com.nagarro.miniassignment.dto;

import java.util.List;

import com.nagarro.miniassignment.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponseDTO {
    private List<User> users;
    private PageInfo pageInfo;

   
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageInfo {
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private long total;

    }
}


