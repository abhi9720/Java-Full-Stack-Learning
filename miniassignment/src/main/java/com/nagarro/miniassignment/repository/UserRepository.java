package com.nagarro.miniassignment.repository;

import org.springframework.stereotype.Repository;
import com.nagarro.miniassignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
