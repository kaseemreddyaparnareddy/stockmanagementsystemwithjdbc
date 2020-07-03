package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjdbc.dao.InvestorDAO;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjdbc.utility.Utility;

public class InvestorDAOImplementation implements InvestorDAO {
	InvestorBean investorBean = new InvestorBean();
	Utility utility = new Utility();

	public boolean investorRegistration(InvestorBean investor) {
		try (Connection connection = utility.getConnection()) {

			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("addInvestor"))) {
				pstmt.setInt(1, investor.getId());
				pstmt.setString(2, investor.getName());
				pstmt.setString(3, investor.getEmail());
				pstmt.setString(4, investor.getPassword());
				pstmt.setLong(5, investor.getMobile_number());
				pstmt.setString(6, investor.getRole());

				pstmt.executeUpdate();
			}

		} catch (Exception e) {

			throw new StockManagementSystemExceptions("investor already registered");
		}
		return true;
	}

	public boolean investorLogin(String email, String password) {

		try (Connection conn = utility.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("investorLogin"))) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					investorBean.setEmail(rs.getString("email"));
					investorBean.setPassword(rs.getString("password"));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new StockManagementSystemExceptions("Invalid login Credentials");

	}

	public StockBean searchStock(String stockName) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("searchStock"))) {
				StockBean stockBean = new StockBean();
				pstmt.setString(1, stockName);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					stockBean.setStockId(rs.getInt("stockid"));
					stockBean.setStockName(rs.getString("stock_name"));
					stockBean.setPrice(rs.getDouble("price"));
					return stockBean;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementSystemExceptions("No stock found");
	}

	public boolean buyStock(int investorId, int stockid) {

		int reqestedStockId = 0;

		try (Connection connection = utility.getConnection();
				Statement isReqExists = connection.createStatement();
				PreparedStatement reqPstmt = connection.prepareStatement(utility.getQuery("insertStockRequest"));) {

			try (ResultSet resultSet1 = isReqExists.executeQuery(utility.getQuery("showRequest"))) {
				while (resultSet1.next()) {
					reqestedStockId = resultSet1.getInt("stockid");
					if (reqestedStockId == stockid) {
						throw new StockManagementSystemExceptions(
								"stock is already requested so request can't be placed");
					}
				}

			}

				if (stockid != 0) {
					reqPstmt.setInt(1, investorId);
					reqPstmt.setInt(2, stockid);
					reqPstmt.executeUpdate();

					return true;
				} else {
					throw new StockManagementSystemExceptions("stock Is Not Available To Buy");
				}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		}
	}

	public List<StockBean> getAllStocks() {
		try (Connection conn = utility.getConnection()) {
			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(utility.getQuery("getAllStocks"));
				List<StockBean> beans = new ArrayList<StockBean>();

				while (rs.next()) {
					StockBean stockBean1 = new StockBean();
					stockBean1.setStockId(rs.getInt("stockid"));
					stockBean1.setStockName(rs.getString("stock_name"));
					stockBean1.setPrice(rs.getDouble("price"));
					beans.add(stockBean1);
				}
				if (beans.isEmpty()) {
					throw new StockManagementSystemExceptions("No stocks present");
				} else {
					return beans;
				}
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		}

	}

}
