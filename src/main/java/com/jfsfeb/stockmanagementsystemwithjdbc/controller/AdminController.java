package com.jfsfeb.stockmanagementsystemwithjdbc.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.services.AdminService;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.StockManagementSystemValidations;

import lombok.extern.log4j.Log4j;

@Log4j
public class AdminController {
	public static Scanner scanner = new Scanner(System.in);
	AdminBean adminBean = new AdminBean();
	StockRequestBean stockRequestBean = new StockRequestBean();
	CompanyBean companyBean = new CompanyBean();
	ManagerBean managerBean = new ManagerBean();
	AdminService adminService = StockManagementSystemFactory.getAdminServiceImplementation();
	StockManagementSystemValidations validation = new StockManagementSystemValidations();

	public static int checkChoice() {
		boolean select = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				select = true;
			} catch (InputMismatchException e) {
				select = false;
				log.error("Invalid input, Should Contain Only Digits Eg:1");
				scanner.next();
			}
		} while (!select);

		return choice;
	}

	int choose;

	public void adminController() {

		do {
			log.info(" 1. Add Manager");
			log.info(" 2. Remove Manager");
			log.info(" 3. Add Company");
			log.info(" 4. Remove Company");
			log.info(" 5. Get list of investors");
			log.info(" 6. Investors stocks request");
			log.info(" 7. Accept stocks");
			log.info(" 8. Get list of managers");
			log.info(" 9. Get list of companies");
			log.info(" 0. Logout");
			choose = checkChoice();

			switch (choose) {
			case 1:
				log.info("Enter Manager Id");
				int managerId = 0;
				String managerName = null, managerEmail = null, password = null, companyName = null;
				managerId = (int) (Math.random() * 1000);
				if (managerId <= 100) {
					managerId = managerId + 100;
				}
				log.info(managerId);
				managerBean.setUserId(managerId);
				log.info("Enter manager name");
				try {
					managerName = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setName(managerName);
				scanner.nextLine();
				log.info("Enter manager Email");
				try {
					managerEmail = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setEmail(managerEmail);
				scanner.nextLine();
				log.info("Enter password");
				try {
					password = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setPassword(password);
				scanner.nextLine();
				log.info("Enter company name");
				try {
					companyName = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setCompanyName(companyName);
				log.info("Enter role");
				String role = LoginController.checkRole();
				managerBean.setRole(role);
				try {
					boolean isAdded = adminService.addManager(managerBean);
					if (isAdded) {
						log.info("Manager added successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 2:
				log.info("Remove manager");
				int id = 0;
				log.info("Enter manager Id");
				try {
					id = scanner.nextInt();
				} catch (InputMismatchException e) {
					log.error(e.getMessage());
					scanner.nextLine();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				managerBean.setUserId(id);
				try {
					boolean isRemoved = adminService.removeManager(id);
					if (isRemoved) {
						log.info("Removed successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 3:
				log.info("Add company");
				log.info("Company id");
				int companyId=0;
				companyId = (int) (Math.random() * 1000);
				if (companyId <= 100) {
					companyId = companyId + 100;
				}
				log.info(companyId);
				companyBean.setCompanyId(companyId);
				log.info("Enter company name");
				String companyNamee = null, companyBranch = null, companyState = null;
				try {
					companyNamee = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				log.info("Enter company branch");
				try {
					companyBranch = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				log.info("Enter company state");
				try {
					companyState = scanner.next();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				companyBean.setCompanyName(companyNamee);
				companyBean.setBranch(companyBranch);
				companyBean.setState(companyState);
				try {
					boolean isToAdd = adminService.addCompany(companyBean);
					if (isToAdd) {
						log.info("company added successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 4:
				log.info("Remove company");
				log.info("Enter company Id");
				int id1 = 0;
				try {
					id1 = scanner.nextInt();
				} catch (InputMismatchException e) {
					log.error(e.getMessage());
					scanner.nextLine();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}
				companyBean.setCompanyId(id1);
				try {
					boolean isRemoved = adminService.removeCompany(id1);
					if (isRemoved) {
						log.info("Removed successfully");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 5:
				log.info("List of investors");
				List<InvestorBean> investorsList = adminService.getAllInvestors();
				for (InvestorBean investor : investorsList) {
					System.out.println("---------------------------------------------------");
					System.out.println("Investor Id------>" +investor.getId());
					System.out.println("Investor name---->" +investor.getName());
					System.out.println("Investor stocks-->" +investor.getNumberOfStocks());
					System.out.println("Investor EmailId-->"+investor.getEmail());
					System.out.println("---------------------------------------------------");
				}

				break;
			case 6:
				log.info("Get investors stocks request");
				List<StockRequestBean> reqInfo = adminService.investorRequest();
				for (StockRequestBean investorRequest : reqInfo) {
					log.info("---------------------------------------------------");
					log.info("Investor Id------->" + investorRequest.getInvestorId());
					log.info("Investor stockId-->" + investorRequest.getStockId());
					log.info("---------------------------------------------------");
				}
				break;

			case 7:
				log.info("Stocks to be accepted");
				log.info("Enter stock id");
				int investorId = 0, stockId = 0;
				try {
					stockId = scanner.nextInt();
				stockRequestBean.setStockId(stockId);
				log.info("Enter investorid");
					investorId = scanner.nextInt();
				} catch (StockManagementSystemExceptions smse) {
					log.error(smse.getMessage());
				}catch(InputMismatchException e) {
					log.error("Enter valid id, should contain only 3 digits");
				}
				stockRequestBean.setInvestorId(investorId);
				log.info("-------------------------");
				try {
					boolean isAccepted = adminService.acceptStock(stockId, investorId);
					if (isAccepted) {
						log.info("Stock accepted");
					}
				} catch (StockManagementSystemExceptions smse) {
					log.info(smse.getMessage());
				}
				break;
			case 8:
				log.info("List of managers");
				List<ManagerBean> managersList = adminService.getAllManagers();
				for (ManagerBean managers : managersList) {
					System.out.println("---------------------------------------------------");
					System.out.println("Manager Id------>" +managers.getUserId());
					System.out.println("Manager name---->" +managers.getName());
					System.out.println("Manager EmailId->"+managers.getEmail());
					System.out.println("Company name---->"+managers.getCompanyName());
					System.out.println("---------------------------------------------------");
				}
				break;
			case 9:
				log.info("List of companies");
				List<CompanyBean> companyList = adminService.getAllCompanies();
				for (CompanyBean company : companyList) {
					System.out.println("---------------------------------------------------");
					System.out.println("Company Id------>" +company.getCompanyId());
					System.out.println("Company name---->" +company.getCompanyName());
					System.out.println("Company branch-->" +company.getBranch());
					System.out.println("Company state--->" +company.getState());
					System.out.println("---------------------------------------------------");
				}

				break;
			case 0:
				log.info("logout sucessfull");
				break;
			default:
				log.error("Invalid choice, should choose from 0 to 8");
				break;
			}

		} while (choose != 0);

	}

	public void adminLogin() {
		log.info("Admin Login Page");
		log.info("-----------------");
		log.info("Enter Email id");
		String adminEmailId = null, adminPassword = null;
		try {
			adminEmailId = scanner.next();
		} catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}

		log.info("Enter password");
		try {
			adminPassword = scanner.next();
		} catch (StockManagementSystemExceptions smse) {
			log.error(smse.getMessage());
		}

		try {
			boolean login = adminService.adminLogin(adminEmailId, adminPassword);
			if (login) {
				adminController();
			}
		} catch (StockManagementSystemExceptions smse) {
			log.info(smse.getMessage());
		}
	}

}
