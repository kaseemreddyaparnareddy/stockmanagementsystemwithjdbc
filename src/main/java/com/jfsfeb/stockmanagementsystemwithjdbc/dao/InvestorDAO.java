package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;

public interface InvestorDAO {
	boolean investorRegistration(InvestorBean investor);

	boolean investorLogin(String email, String password);

	public StockBean searchStock(String stockName);

	List<StockBean> getAllStocks();

	boolean buyStock(int investorId, int stockid);

}
