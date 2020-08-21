package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
//	select * from user where username=1?;
	Optional<User> findByUsername(String username);
}

//	JPA Naming Query
//	select * from user where username=?1 and password=?2
//	User findByUsernameAndPassword(String username, String password);

//	@Query(value="select * from user where username=?1 and password=?2", nativeQuery = true)
//	User login(String username, String password);