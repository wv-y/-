package com.example.demo.dao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entity.OrderEntity;


@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, String>{

	public List<OrderEntity> findByusername(String name);
}
