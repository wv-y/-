package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.Controller;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.repo.ProductRepo;
import com.example.demo.domain.Product;
import com.example.demo.domain.Result;
import com.example.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo proRepo;

	// 查找正常的商品
	@Override
	public List<Product> findProZheng() {
		List<ProductEntity> proEntities = this.proRepo.findByprotype("正常");
		List<Product> pros = proEntities.stream().map(entity -> {
			Product pro = new Product();
			BeanUtils.copyProperties(entity, pro);
			return pro;
		}).collect(Collectors.toList());
		for (int i = 0; i < pros.size(); i++) {
			if (pros.get(i).getPronum() <= 0) {
				pros.remove(i);
			}
		}
		return pros;
	}

	// 查找所有的商品
	public List<Product> findAllpro() {
		List<ProductEntity> proEntities = this.proRepo.findAll();
		List<Product> pros = proEntities.stream().map(entity -> {
			Product pro = new Product();
			BeanUtils.copyProperties(entity, pro);
			return pro;
		}).collect(Collectors.toList());
		return pros;
	}

	// 根据id查找商品
	@Override
	public Product findbyid(String id) {
		ProductEntity productEn = new ProductEntity();
		Product pro = new Product();
		productEn = proRepo.findById(id).get();
		pro.setProid(productEn.getProid());
		pro.setProname(productEn.getProname());
		pro.setPronum(productEn.getPronum());
		pro.setProprice(productEn.getProprice());
		pro.setProtype(productEn.getProtype());
		return pro;
	}

	// 修改库存
	@Override
	public void updateNum(String id, int num) {
		proRepo.updateProNum(id, num);
	}

	// 添加商品
	@Override
	public Result addproduct(Product p) {
		Result res = new Result();
		try {
			ProductEntity pEn = new ProductEntity();
			pEn.setProid(p.getProid());
			pEn.setProname(p.getProname());
			pEn.setPronum(p.getPronum());
			pEn.setProtype(p.getProtype());
			pEn.setProprice(p.getProprice());
			proRepo.save(pEn);
			res.setCode(100);
		} catch (Exception e) {
			res.setCode(200);
		}
		return res;
	}

	// 更新商品状态
	@Override
	public Result updateProtype(String id, String type) {
		Result res = new Result();
		log.info("更新" + type + id);

		try {
			proRepo.updateProType(id, type);
			res.setCode(100);
		} catch (Exception e) {
			res.setCode(200);
		}

		return res;
	}

}
