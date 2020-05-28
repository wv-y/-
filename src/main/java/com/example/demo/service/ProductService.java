package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Product;
import com.example.demo.domain.Result;

public interface ProductService {

	public List<Product> findProZheng();
	
	public Product findbyid(String id);
	
	public void updateNum(String id, int num);
	
	public Result addproduct(Product p);
	
	public List<Product> findAllpro();
	
	//更新商品状态
	public Result updateProtype(String id ,String type);
}
