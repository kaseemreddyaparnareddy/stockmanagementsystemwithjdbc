package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjdbc.utility.Utility;

public class AdminDAOImplementation implements AdminDAO {
	AdminBean adminBean = new AdminBean();
	Utility utility = new Utility();

	public boolean adminLogin(String email, String password) {

		try (Connection conn = utility.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("login"))) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					adminBean.setEmail(rs.getString("email"));
					adminBean.setPassword(rs.getString("password"));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new StockManagementSystemExceptions("Invalid login Credentials");

	}

	public boolean addManager(ManagerBean managerBean) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("addManager"))) {
				pstmt.setInt(1, managerBean.getUserId());
				pstmt.setString(3, managerBean.getEmail());
				pstmt.setString(4, managerBean.getPassword());
				pstmt.setString(5, managerBean.getRole());
				pstmt.setString(2, managerBean.getName());

				int result = pstmt.executeUpdate();
				if (result > 0) {
					return true;
				} else {
					return false;
				}

			}
		} catch (Exception e) {

			e.getMessage();
		}
		throw new StockManagementSystemExceptions("Manager with same id and name already exists");
	}

	public boolean removeManager(int userId) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("removeManager"))) {
				pstmt.setInt(1, userId);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			throw new StockManagementSystemExceptions("Manager is already removed");
		}

		return true;
	}

	public boolean addCompany(CompanyBean companyBean) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("addCompany"))) {
				pstmt.setInt(1, companyBean.getCompanyId());
				pstmt.setString(2, companyBean.getCompanyName());
				pstmt.setString(3, companyBean.getBranch());
				pstmt.setString(4, companyBean.getState());

				pstmt.executeUpdate();

			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions("Company with same id and name already exists");

		}

		return true;
	}

	public boolean removeCompany(int companyId) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("removeCompany"))) {
				pstmt.setInt(1, companyId);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			throw new StockManagementSystemExceptions("Company is already removed");
		}

		return true;
	}

	public List<InvestorBean> getAllInvestors() {
		try (Connection conn = utility.getConnection()) {
			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(utility.getQuery("getAllInvestors"));
				List<InvestorBean> beans = new ArrayList<InvestorBean>();
				while (rs.next()) {
					InvestorBean investorBean = new InvestorBean();
					investorBean.setId(rs.getInt("user_id"));
					investorBean.setName(rs.getString("name"));
					investorBean.setEmail(rs.getString("email"));
					investorBean.setNumberOfStocks(rs.getInt("numberOfStocks"));
					beans.add(investorBean);
				}
				if (beans.isEmpty()) {
					throw new StockManagementSystemExceptions("No investors present");
				}
				return beans;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		}

	}

	public List<StockRequestBean> investorRequest() {
		try (Connection conn = utility.getConnection()) {

			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(utility.getQuery("showRequest"));
				List<StockRequestBean> beans = new ArrayList<StockRequestBean>();
				while (rs.next()) {
					StockRequestBean requestInfo = new StockRequestBean();
					requestInfo.setInvestorId(rs.getInt("user_id"));
					requestInfo.setStockId(rs.getInt("stockid"));
					beans.add(requestInfo);

				}
				if (beans.isEmpty()) {
					throw new StockManagementSystemExceptions("No requests Found");
				} else {
					return beans;
				}

			}

		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		}

	}

	public boolean acceptStock(int stockId, int investorId) {
		int number_of_stocks=0;
		try (Connection connection = utility.getConnection();
				PreparedStatement requestpstmt = connection.prepareStatement(utility.getQuery("getRequest"));
				PreparedStatement userPstmt = connection.prepareStatement(utility.getQuery("getUserStocks"));
				PreparedStatement acceptPstmt = connection.prepareStatement(utility.getQuery("acceptStockQuery"));
				PreparedStatement setStocksBorrowedStmt = connection
						.prepareStatement(utility.getQuery("setNoOfStocksBorrowed"));) {
			
			requestpstmt.setInt(1, stockId);
			requestpstmt.setInt(2, investorId);
			try (ResultSet requestResultSet = requestpstmt.executeQuery();) {

				if (requestResultSet.next()) {
					int requestUserId = requestResultSet.getInt("user_id");
					userPstmt.setInt(1, requestUserId);
					try (ResultSet userResultSet = userPstmt.executeQuery();) {

						if (userResultSet.next()) {
							InvestorBean users = new InvestorBean();
							users.setNumberOfStocks(userResultSet.getInt("numberOfStocks"));
							int noOfStocksBorrowed = users.getNumberOfStocks();

							acceptPstmt.setInt(1, number_of_stocks);
							acceptPstmt.setInt(2, investorId);
							if (noOfStocksBorrowed < 3) {
								noOfStocksBorrowed++;
								setStocksBorrowedStmt.setInt(1, noOfStocksBorrowed);
								setStocksBorrowedStmt.setInt(2, requestUserId);
								setStocksBorrowedStmt.executeUpdate();

							} else {
								throw new StockManagementSystemExceptions(
										"Invalid Request Id as limit of stocks to buy has exceeded");
							}
						}
					}

				} else {
					throw new StockManagementSystemExceptions("This Stock is Already sold ");
				}
			}

		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());

		}
		return true;
	}

	@Override
	public List<ManagerBean> getAllManagers() {
		try (Connection conn = utility.getConnection()) {
			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(utility.getQuery("getAllManagers"));
				List<ManagerBean> beans = new ArrayList<ManagerBean>();
				while (rs.next()) {
					ManagerBean managerBean = new ManagerBean();
					managerBean.setUserId(rs.getInt("user_id"));
					managerBean.setName(rs.getString("name"));
					managerBean.setEmail(rs.getString("email"));
					managerBean.setCompanyName(rs.getString("company_name"));
					beans.add(managerBean);
				}
				if (beans.isEmpty()) {
					throw new StockManagementSystemExceptions("No manager present");
				}
				return beans;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		}

	}

	@Override
	public List<CompanyBean> getAllCompanies() {
		try (Connection conn = utility.getConnection()) {
			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(utility.getQuery("getAllCompanies"));
				List<CompanyBean> beans = new ArrayList<CompanyBean>();
				while (rs.next()) {
					CompanyBean companyBean = new CompanyBean();
					companyBean.setCompanyId(rs.getInt("companyId"));
					companyBean.setCompanyName(rs.getString("companyName"));
					companyBean.setBranch(rs.getString("companyBranch"));
					;
					companyBean.setState(rs.getString("companyState"));
					beans.add(companyBean);
				}
				if (beans.isEmpty()) {
					throw new StockManagementSystemExceptions("No company present");
				}
				return beans;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		}

	}

}
