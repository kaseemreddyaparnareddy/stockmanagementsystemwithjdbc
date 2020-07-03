package com.jfsfeb.stockmanagementsystemwithjdbc.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class CompanyBean implements Serializable {
	private int companyId;
	private String companyName;
	private String branch;
	private String state;

}
