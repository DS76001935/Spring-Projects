package com.SpringJPADemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringJPADemo.Model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	UserEntity findByEmail(String email);
	UserEntity findByPassword(String password);
	UserEntity findById(int userId);

}
