package com.nagarro.miniassignment.repository;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.miniassignment.dto.UserListResponseDTO;
import com.nagarro.miniassignment.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	private final EntityManager entityManager;

	@Autowired
	public UserRepositoryCustomImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public UserListResponseDTO findUsersWithOffsetAndLimit(int offset, int limit) {
		String jpql = "SELECT u FROM User u ORDER BY u.id";
		Query query = entityManager.createQuery(jpql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);


		List<User> users = query.getResultList();

		long totalUsers = getTotalCount();

		
 		
		UserListResponseDTO.PageInfo pageInfo = new UserListResponseDTO.PageInfo();
        pageInfo.setHasPreviousPage(offset > 0 && totalUsers > 0); 
        pageInfo.setHasNextPage( offset + limit < totalUsers);
        pageInfo.setTotal(totalUsers);

        UserListResponseDTO responseDTO = new UserListResponseDTO();
        responseDTO.setUsers(users);
        responseDTO.setPageInfo(pageInfo);

        return responseDTO;
	}
	
	
	private long getTotalCount() {
        String countQuery = "SELECT COUNT(u) FROM User u";
        Query query = entityManager.createQuery(countQuery);
        return (long) query.getSingleResult();
    }
}