package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.Controller;
import com.example.demo.dao.entity.OrderEntity;
import com.example.demo.dao.repo.OrderRepo;
import com.example.demo.domain.Order;
import com.example.demo.domain.Result;
import com.example.demo.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo orderRepo;

	// 创建订单
	@Override
	public boolean buypro(OrderEntity orderEn) {
		try {
			orderRepo.save(orderEn);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 查找订单
	@Override
	public List<Order> findbyusername(String name) {
		List<OrderEntity> proEntities = this.orderRepo.findByusername(name);
		log.info("orderRepo" + proEntities);
		List<Order> orders = proEntities.stream().map(entity -> {
			Order o = new Order();
			BeanUtils.copyProperties(entity, o);
			return o;
		}).collect(Collectors.toList());
		log.info("listOrder" + orders);
		return orders;
	}

}
