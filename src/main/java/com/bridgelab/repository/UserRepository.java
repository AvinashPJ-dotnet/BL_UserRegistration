package com.bridgelab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelab.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findUserByUsernameAndPassword(String username,String password);
}
