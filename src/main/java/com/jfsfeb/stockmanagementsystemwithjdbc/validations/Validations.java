package com.jfsfeb.stockmanagementsystemwithjdbc.validations;

public interface Validations {
	public boolean validateByName(String name);

	public boolean validateByEmail(String email);

	public boolean validateByUserId(String userId);

	public boolean validateByStocksId(String stocksId);

	public boolean validateByPhoneNumber(String phoneNumber);

	public boolean validateByPrice(String price);

	public boolean passwordValidation(String password);
}
