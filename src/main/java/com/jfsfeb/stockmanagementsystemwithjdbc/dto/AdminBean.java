package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String name;
	private String password;
	private String email;

}
