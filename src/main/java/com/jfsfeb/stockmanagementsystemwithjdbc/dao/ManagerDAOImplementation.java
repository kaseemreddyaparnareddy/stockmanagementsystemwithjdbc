package com.jfsfeb.stockmanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfsfeb.stockmanagementsystemwithjdbc.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjdbc.utility.Utility;

public class ManagerDAOImplementation implements ManagerDAO {
	ManagerBean managerBean = new ManagerBean();
	Utility utility = new Utility();

	public boolean managerLogin(String email, String password) {
		try (Connection conn = utility.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(utility.getQuery("managerLogin"))) {
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					managerBean.setEmail(rs.getString("email"));
					managerBean.setPassword(rs.getString("password"));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new StockManagementSystemExceptions("Invalid login Credentials");

	}

	public boolean changePassword(String email, String oldPassword, String newPassword) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("changePassword"))) {
				pstmt.setString(1, newPassword);
				pstmt.setString(2, email);
				pstmt.setString(3, oldPassword);
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new StockManagementSystemExceptions("Unable to update password");

	}

	public boolean addStock(StockBean bean) {
		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("addStock"))) {
				pstmt.setInt(1, bean.getStockId());
				pstmt.setString(2, bean.getStockName());
				pstmt.setDouble(3, bean.getPrice());

				pstmt.executeUpdate();

			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions("stock with same id and name already exists");

		}

		return true;
	}

	public boolean removeStock(int stockId) {

		try (Connection connection = utility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(utility.getQuery("removeStock"))) {
				pstmt.setInt(1, stockId);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			throw new StockManagementSystemExceptions("stock is already removed");
		}

		return true;
	}

}
