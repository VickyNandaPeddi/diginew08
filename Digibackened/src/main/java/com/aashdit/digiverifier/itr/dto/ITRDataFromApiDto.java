package com.aashdit.digiverifier.itr.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ITRDataFromApiDto {

	String deductor;
	String tan;
	String amount;
	String tds;
	String date;
	String section;
	String assesmentYear;
	String financialYear;
	Date filedDate;
}
