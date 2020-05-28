package com.example.demo.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repo.UserRepo;
import com.example.demo.domain.Result;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Transactional
	@Override
	public Result setPass(String name, String paw) {
		Result res = new Result();
		try {
			log.info("userservice"+name+paw);
			userRepo.setPassRepo(name, paw);
			res.setCode(100);
		}
		catch(Exception e) {
			res.setCode(200);
		}
		return res;
	}
}
