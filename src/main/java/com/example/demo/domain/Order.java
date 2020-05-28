package com.example.demo.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String orderid;
	private String proid;
	private String proname;
	private String username;
	private String ordertime;
	private int ordernum;
	private int ordertotal;
}
