package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.entity.OrderEntity;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderTotal;

public interface OrderService {

	public boolean buypro(OrderEntity orderEn);	//购买商品创建订单
	
	public List<Order> findbyusername(String name);//根据用户名查找订单
}
