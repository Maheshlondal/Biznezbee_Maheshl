package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
    // Custom query methods can be added here
	 List<User> findByAgeLessThanEqual(Integer age);

	boolean existsByMobile(String mobile);

	List<User> findByUserType(String userType);

	List<User> findByAgeBetween(Integer ageGrater, Integer ageLessthan);

	List<User> findByAgeGreaterThanEqual(Integer ageGrater);

	List<User> findByUserTypeAndAgeBetween(String userType, Integer ageGrater, Integer ageLessthan);
}
