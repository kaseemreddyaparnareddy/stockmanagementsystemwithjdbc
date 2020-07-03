package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StockRequestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int stockId;
	private int investorId;
	private String investorName;
	private String stockName;

}
