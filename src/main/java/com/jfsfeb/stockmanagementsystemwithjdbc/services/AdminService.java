package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockRequestBean;

public interface AdminService {
	boolean adminLogin(String email, String password);

	boolean addManager(ManagerBean managerBean);

	boolean removeManager(int userId);

	boolean addCompany(CompanyBean companyBean);

	boolean removeCompany(int companyId);

	List<InvestorBean> getAllInvestors();

	List<StockRequestBean> investorRequest();

	boolean acceptStock(int stockId, int investorId);

	List<ManagerBean> getAllManagers();

	List<CompanyBean> getAllCompanies();
}
