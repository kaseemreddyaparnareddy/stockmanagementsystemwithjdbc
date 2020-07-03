package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;

public interface ManagerService {

	boolean managerLogin(String email, String password);

	boolean changePassword(String email, String oldPassword, String newPassword);

	boolean addStock(StockBean bean);

	boolean removeStock(int stockId);

}