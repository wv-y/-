package com.example.demo.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="admin_table")
public class AdminEntity {

	@Id
	private String adminname;
	
	@Column
	private String adminpaw;
}
