package com.tw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	public Optional<User> findByUserName(String username);

	public Optional<User> findOneByUserName(String userName);

	public User findOneById(Long id);

	public Boolean existsByUserName(String username);

	User findByUserNameAndPassword(String userName, String password);

}
