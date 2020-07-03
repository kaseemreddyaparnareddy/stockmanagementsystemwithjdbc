package com.jfsfeb.stockmanagementsystemwithjdbc.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StockManagementSystemValidations implements Validations{

	public boolean validateByName(String name) {
		Pattern pattern = Pattern.compile("^[A-Za-z\\s]+$");
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean validateByEmail(String email) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z]+([.][a-zA-Z]+)+");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean validateByUserId(String userId) {
		Pattern pattern = Pattern.compile("^[0-9]{3}$");
		Matcher matcher = pattern.matcher(userId);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean validateByStocksId(String stocksId) {
		Pattern pattern = Pattern.compile("^[0-9]{3}$");
		Matcher matcher = pattern.matcher(stocksId);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean validateByPhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern.compile("^[0-9]{10}$");
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean validateByPrice(String price) {
		Pattern pattern = Pattern.compile("[0-9]+([,.][0-9]{1,3})?");
		Matcher matcher = pattern.matcher(price);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean passwordValidation(String password) {
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,15}$");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}
