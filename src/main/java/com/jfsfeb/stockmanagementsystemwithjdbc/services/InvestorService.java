package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;

public interface InvestorService {
	boolean investorRegistration(InvestorBean investor);

	boolean investorLogin(String email, String password);

	public StockBean searchStock(String stockName);

	boolean buyStock(int investorId, int stockid);

	List<StockBean> getAllStocks();

}
