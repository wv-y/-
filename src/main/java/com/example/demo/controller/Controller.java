package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.AdminEntity;
import com.example.demo.dao.entity.OrderEntity;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repo.AdminRepo;
import com.example.demo.dao.repo.OrderRepo;
import com.example.demo.dao.repo.ProductRepo;
import com.example.demo.dao.repo.UserRepo;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderTotal;
import com.example.demo.domain.Product;
import com.example.demo.domain.Result;
import com.example.demo.domain.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("http://localhost:8081")
public class Controller {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserService userService;

	@Autowired
	private ProductRepo proRepo;
	@Autowired
	private ProductService proService;

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private OrderService orderService;

	@Autowired
	private AdminRepo adminRepo;

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/login")
	public Result findUser(@RequestBody User u) {
		String name = u.getUsername();
		String paw = u.getUserpaw();
		Result res = new Result();
		log.info("login" + name);
		try {
			UserEntity userEn = userRepo.findById(name).get();
			if (paw.equals(userEn.getUserpaw())) { // 密码匹配
				res.setCode(100);
			} else
				res.setCode(200); // 密码不匹配
		} catch (Exception e) { // 找不到用户
			res.setCode(300);
		}
		return res;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/logon")
	public Result userLogon(@RequestBody User u) {
		String name = u.getUsername();
		String paw = u.getUserpaw();
		Result res = new Result();
		log.info("logon" + name);
		try {
			userRepo.findById(name).get();
			res.setCode(200); // 用户已存在
		} catch (Exception e) {
			UserEntity userEn = new UserEntity();
			userEn.setUsername(name);
			userEn.setUserpaw(paw);
			userRepo.save(userEn);
			res.setCode(100);
		}
		return res;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/shop/setpass")
	public Result setPass(@RequestBody User u) {
		log.info("setpass " + u);
		Result res = new Result();
		res = userService.setPass(u.getUsername(), u.getUserpaw());
		log.info("setpass  res  " + res);
		return res;
	}

	// 查找可售卖的商品
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/shop/allproduct")
	public List<Product> allPro() {
		// Pageable pageable = PageRequest.of(0,10);
		return proService.findProZheng();
	}

	// 查看库存是否足够，然后购买商品,
	// 再将库存做减法
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/shop/buypro")
	public OrderTotal buypro(@RequestBody Order o) {
		log.info("goumai" + o);
		OrderTotal total = new OrderTotal();
		Product pro = new Product();
		OrderEntity oEn = new OrderEntity();
		String proid = o.getProid();
		int num = o.getOrdernum();
		pro = proService.findbyid(proid);
		// 库存不足
		if (num > pro.getPronum()) {
			total.setFlag(false);
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			oEn.setOrderid(o.getUsername() + df.format(new Date()) + "order"); // new Date()为获取当前系统时间
			oEn.setProid(o.getProid());
			oEn.setProname(o.getProname());
			oEn.setUsername(o.getUsername());
			oEn.setOrdertime(df.format(new Date()));
			oEn.setOrdernum(o.getOrdernum());
			oEn.setOrdertotal(num * pro.getProprice());
			if (orderService.buypro(oEn)) {
				log.info("购买成功" + oEn);
				total.setFlag(true);
				total.setTotal(num * pro.getProprice());
				// 购买成功把商品库存，做减法
				proService.updateNum(pro.getProid(), pro.getPronum() - num);

			}
		}
		return total;
	}

	// 查看历史订单
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/shop/seeOrder")
	public List<Order> findAllOrder(@RequestBody User u) {
		log.info("seeorder" + u);
		String name = u.getUsername();
		return orderService.findbyusername(name);
	}

	// 添加商品
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/shop/addpro")
	public Result addPro(@RequestBody Product p) {
		log.info("addpro " + p);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		p.setProid(df.format(new Date()) + p.getProname() + " pro");
		p.setProtype("待审核");
		log.info("addpro add" + p);
		return proService.addproduct(p);
	}

	// 管理员登录
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/loginAdmin")
	public Result findAdmin(@RequestBody Admin admin) {
		String name = admin.getAdminname();
		String paw = admin.getAdminpaw();
		Result res = new Result();
		log.info("login" + name);
		try {
			AdminEntity adminEn = adminRepo.findById(name).get();
			if (paw.equals(adminEn.getAdminpaw())) { // 密码匹配
				res.setCode(100);
			} else
				res.setCode(200); // 密码不匹配
		} catch (Exception e) { // 找不到用户
			res.setCode(300);
		}
		return res;
	}

	// 管理员页面商品
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/admin/allproduct")
	public List<Product> findAllpro() {

		return proService.findAllpro();
	}

	// 上架商品
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/admin/putOnproduct")
	public Result proOn(@RequestBody Product p) {
		log.info("putOn" + p.getProid());
		Result res = new Result();
		res = proService.updateProtype(p.getProid(), "正常");
		return res;
	}

	// 下架商品
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/admin/putOffproduct")
	public Result proOff(@RequestBody Product p) {
		log.info("putOn" + p.getProid());
		Result res = new Result();
		res = proService.updateProtype(p.getProid(), "下架");
		return res;
	}

}
