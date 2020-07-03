package com.jfsfeb.stockmanagementsystemwithjdbc.services;

import com.jfsfeb.stockmanagementsystemwithjdbc.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystemwithjdbc.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjdbc.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjdbc.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystemwithjdbc.validations.StockManagementSystemValidations;

public class ManagerServiceImplementation implements ManagerService {
	private ManagerDAO dao = StockManagementSystemFactory.getManagerDAOImplementation();
	private StockManagementSystemValidations validation = new StockManagementSystemValidations();

	@Override
	public boolean managerLogin(String email, String password) {
		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(password)) {

				return dao.managerLogin(email, password);
			} else {
				throw new StockManagementSystemExceptions("Enter valid password, Eg:xyz@gmail.com");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid emailId");
		}
	}

	@Override
	public boolean changePassword(String email, String oldPassword, String newPassword) {
		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(oldPassword)) {
				if (validation.passwordValidation(newPassword)) {

					return dao.changePassword(email, oldPassword, newPassword);
				} else {
					throw new StockManagementSystemExceptions(
							"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ");
				}
			} else {
				throw new StockManagementSystemExceptions(
						"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid Email id, Eg:xyz@gmail.com");
		}

	}

	@Override
	public boolean addStock(StockBean bean) {
		if (validation.validateByName(bean.getStockName())) {
			if (validation.validateByPrice(Double.toString(bean.getPrice()))) {
				return dao.addStock(bean);
			} else {
				throw new StockManagementSystemExceptions("Enter valid price, should contain only 3 digits");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid name, should contain only characters");
		}

	}

	@Override
	public boolean removeStock(int stockId) {
		if (validation.validateByStocksId(Integer.toString(stockId))) {

			return dao.removeStock(stockId);
		} else {
			throw new StockManagementSystemExceptions("Enter valid stock id, should contain exactly 3 digits");
		}
	}
}
