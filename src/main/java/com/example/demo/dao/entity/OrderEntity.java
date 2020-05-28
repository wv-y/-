package com.example.demo.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_table")
public class OrderEntity {

	@Id
	private String orderid;
	
	@Column
	private String proid;
	@Column
	private String proname;
	@Column
	private String username;
	@Column
	private String ordertime;
	@Column
	private int ordernum;
	@Column
	private int ordertotal;
	
	/*
	 * public void setOrdertime(String time) { this.ordertime=time; }
	 */
}
