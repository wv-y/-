package com.example.demo.dao.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.domain.User;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

	/*
	 * @Query(value="select * from user_table where name=name1", nativeQuery=true)
	 * public User findUser(@Param("name1") String name);
	 */
	/*@Modifying
	@Query("update user_table ut set ut.userpaw =:paw1 where ut.username =:name1")
	public void setPassRepo(@Param("name1")String name, @Param("paw1")String paw);*/
	
	@Transactional
	@Modifying
	@Query(value="update user_table ut set ut.userpaw =:paw where ut.username =:name",nativeQuery = true)
	public void setPassRepo(String name, String paw);
}
