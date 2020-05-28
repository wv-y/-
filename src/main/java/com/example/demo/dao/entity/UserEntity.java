package com.example.demo.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

	@Data
	@Entity
	@Table(name="user_table")
	public class UserEntity {
		@Id
	   private String username;
	   
	   @Column
	   private String userpaw;
}
