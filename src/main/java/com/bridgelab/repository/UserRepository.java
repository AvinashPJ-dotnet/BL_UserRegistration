package com.bridgelab.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelab.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUsername(String username);

	Optional<User> findByEmail(String email);

	User findByUsername(String username);
}
