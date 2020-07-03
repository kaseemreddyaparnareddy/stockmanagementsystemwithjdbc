package com.jfsfeb.stockmanagementsystemwithjdbc.factory;

import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDAO;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.AdminDAOImplementation;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.InvestorDAO;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.InvestorDAOImplementation;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystemwithjdbc.dao.ManagerDAOImplementation;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.AdminService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.AdminServiceImplementation;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.InvestorService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.InvestorServiceImplementation;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.ManagerService;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.ManagerServiceImplementation;

public class StockManagementSystemFactory {

	private StockManagementSystemFactory() {
		
	}
	public static AdminDAO getAdminDAOImplementation() {
		AdminDAO admin = new AdminDAOImplementation();
		return admin;
	}
	public static InvestorDAO getInvestorDAOImplementation() {
		InvestorDAO investor = new InvestorDAOImplementation();
		return investor;
	}
	public static ManagerDAO getManagerDAOImplementation() {
		ManagerDAO manager = new ManagerDAOImplementation();
		return manager;
	}
	public static AdminService getAdminServiceImplementation() {
		AdminService adminService = new AdminServiceImplementation();
		return adminService;
	}
	public static ManagerService getManagerServiceImplementation() {
		ManagerService managerService = new ManagerServiceImplementation();
		return managerService;
	}
	public static InvestorService getInvestorServiceImplementation() {
		InvestorService investorService = new InvestorServiceImplementation();
		return investorService;
	}
}
