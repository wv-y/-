package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private String proid;
	private String proname;
	private int proprice;
	private String protype;
	private int pronum;
	
}
