package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
public class InvestorBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	@ToString.Exclude
	private String password;
	private int numberOfStocks;
	private long mobile_number;
	private String role;

}
