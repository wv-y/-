package com.example.demo.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="product_table")
public class ProductEntity {

	@Id
	private String proid;
	
	@Column
	private String proname;
	@Column
	private int proprice;
	@Column
	private String protype;
	@Column
	private int pronum;
}
