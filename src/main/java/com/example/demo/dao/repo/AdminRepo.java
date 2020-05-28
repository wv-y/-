package com.example.demo.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.entity.AdminEntity;



public interface AdminRepo extends JpaRepository<AdminEntity, String>{

}
