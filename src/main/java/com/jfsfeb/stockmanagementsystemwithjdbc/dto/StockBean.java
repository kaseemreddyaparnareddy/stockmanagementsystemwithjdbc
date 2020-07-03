package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StockBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int stockId;
	private String stockName;
	private double price;

}
