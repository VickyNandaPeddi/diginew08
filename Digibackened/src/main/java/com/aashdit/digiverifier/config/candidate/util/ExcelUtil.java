package com.aashdit.digiverifier.config.candidate.util;

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
import com.aashdit.digiverifier.config.candidate.model.Candidate;
import com.aashdit.digiverifier.config.candidate.model.SuspectClgMaster;
import com.aashdit.digiverifier.config.candidate.model.SuspectEmpMaster;
// import java.util.concurrent.ThreadLocalRandom; 

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ExcelUtil {
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	  public static boolean hasExcelFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
      
	  public  List<Candidate> excelToCandidate(InputStream is) {
	    try {
	    	  ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
	    	  XSSFWorkbook workbook = new XSSFWorkbook(is);
	          XSSFSheet worksheet = workbook.getSheetAt(0);
			    java.util.Random r = new java.util.Random();
				int start =1000;
				int end = 10000;
				int result = r.nextInt(end-start) + start;
			  for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
				  Candidate candidate = new Candidate();

		            XSSFRow row = worksheet.getRow(i);
		            if(!getCellValue(row, 0).equals("") && !getCellValue(row, 1).equals("")){
		            	candidate.setCandidateName(getCellValue(row, 0));
						candidate.setEmailId(getCellValue(row, 1));
						candidate.setContactNumber(getCellValue(row, 2));
						candidate.setApplicantId(String.valueOf(result));
						candidate.setCcEmailId(getCellValue(row, 3));
						candidate.setExperienceInMonth(!getCellValue(row, 5).equals("")?Integer.valueOf(getCellValue(row, 4)):null);
						candidateList.add(candidate);
		            }
		        }
		      return candidateList;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	  }
	  
	  private String getCellValue(Row row, int cellNo) {
		  String cellValue=null;
		  try {
	        DataFormatter formatter = new DataFormatter();
	        Cell cell = row.getCell(cellNo);
	        cellValue=formatter.formatCellValue(cell);
		  }
		  catch(Exception ex) {
			  log.error("Exception occured in getCellValue method in ExcelUtil-->"+ex);
		  }
		  return cellValue;
	  }

	public List<User> excelToUserList(InputStream inputStream) {
		 try {
	    	  ArrayList<User> userList = new ArrayList<User>();
	    	  XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	          XSSFSheet worksheet = workbook.getSheetAt(0);
			  for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
				  	User user = new User();
		            XSSFRow row = worksheet.getRow(i);
		            if(!getCellValue(row, 0).equals("")){
		            	user.setEmployeeId(getCellValue(row, 0));
			            user.setUserFirstName(getCellValue(row, 1));
			            user.setUserLastName(getCellValue(row, 2));
			            user.setUserEmailId(getCellValue(row, 3));
			            user.setLocation(getCellValue(row, 4));
			            user.setUserMobileNum(getCellValue(row, 5));
			            user.setUserLandlineNum(getCellValue(row, 6));
			            user.setReportingEmailId(getCellValue(row, 7));
			            userList.add(user);
		            }
		            
		        }
		      return userList;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}

	public List<SuspectEmpMaster> excelToSuspectEmpMaster(InputStream inputStream) {
		try {
	    	  ArrayList<SuspectEmpMaster> suspectEmpMasterList = new ArrayList<SuspectEmpMaster>();
	    	  XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	          XSSFSheet worksheet = workbook.getSheetAt(0);
			  for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
				  SuspectEmpMaster suspectEmpMaster = new SuspectEmpMaster();
		            XSSFRow row = worksheet.getRow(i);
		            if(!getCellValue(row, 1).equals("")) {
		            	suspectEmpMaster.setSuspectCompanyName(getCellValue(row, 1));
			            suspectEmpMaster.setAddress(getCellValue(row, 2));
			            suspectEmpMaster.setLocation(getCellValue(row, 3));
			            suspectEmpMaster.setCatagory(getCellValue(row, 4));
			            suspectEmpMaster.setApprovalDate(getCellValue(row, 5));
			            suspectEmpMaster.setVendor(getCellValue(row, 6));
			            suspectEmpMaster.setIsActive(true);
			            suspectEmpMasterList.add(suspectEmpMaster);
		            }
		            
		        }
		      return suspectEmpMasterList;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		   }
	}

	public List<SuspectClgMaster> excelToSuspectClgMaster(InputStream inputStream) {
		try {
	    	  ArrayList<SuspectClgMaster> suspectClgMasterList = new ArrayList<SuspectClgMaster>();
	    	  XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	          XSSFSheet worksheet = workbook.getSheetAt(1);
			  for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
				  SuspectClgMaster suspectClgMaster = new SuspectClgMaster();
		            XSSFRow row = worksheet.getRow(i);
		            if(!getCellValue(row, 0).equals("")) {
		            	suspectClgMaster.setSuspectInstitutionName(getCellValue(row, 0));
			            suspectClgMaster.setAssociatedInstitution(getCellValue(row, 1));
			            suspectClgMaster.setAddress(getCellValue(row, 2));
			            suspectClgMaster.setSource(getCellValue(row, 3));
			            suspectClgMaster.setClassifiedAs(getCellValue(row, 4));
			            suspectClgMaster.setDateModified(getCellValue(row, 5));
			            suspectClgMaster.setVendor(getCellValue(row, 6));
			            suspectClgMaster.setIsActive(true);
			            suspectClgMasterList.add(suspectClgMaster);
		            }
		        }
		      return suspectClgMasterList;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		   }
	}
}
