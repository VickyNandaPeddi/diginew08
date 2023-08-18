package com.aashdit.digiverifier.config.superadmin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.digiverifier.config.admin.model.User;

import com.aashdit.digiverifier.config.superadmin.model.Orgclientscope;


import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j

public class ClientExcelUtil {
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	 public static boolean hasScopeExcelFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	
	  public  List<Orgclientscope> excelToclientscope(InputStream is) {
	    try {
			ArrayList<Orgclientscope> clientscopeList = new ArrayList<Orgclientscope>();
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet worksheet = workbook.getSheetAt(0);
			System.out.println("------------------------------------"+worksheet);
			// for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
			Orgclientscope orgclientscope = new Orgclientscope();
					 	String clientname = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(1));
						String education = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(2));
						String employement = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(3));
						String referencecheck = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(4));
						String criminalcheck = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(5));
						String dbcheck = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(6));
						String add_verify = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(7));
						String denum = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(8));
						String drugtest = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(9));
						String creditcheck = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(10));
						String addremarks = String.valueOf(workbook.getSheetAt(0).getRow(1).getCell(11));
						// String value = workbook.getSheetAt(0).getRow(1).getCell(1);
		            	orgclientscope.setClientName(clientname);
						orgclientscope.setEducation(education);
						orgclientscope.setEmployment(employement);
						orgclientscope.setReferenceCheck1(referencecheck);
						orgclientscope.setCriminalCheck(criminalcheck);
						orgclientscope.setDbCheck(dbcheck);
						orgclientscope.setAddressVerification(add_verify);
						orgclientscope.setID_ENUM_PP_PAN_AADHAR_DL(denum);
						orgclientscope.setDrugTest(drugtest);
						orgclientscope.setCreditCheck(creditcheck);
						orgclientscope.setAdditionalRemarks(addremarks);
						clientscopeList.add(orgclientscope);

		            // }
		        
			System.out.println("--------------------------------------------------------------------"+clientscopeList);
			return clientscopeList;
		}
		catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	  }
}

	