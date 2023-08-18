package com.aashdit.digiverifier.common.dto;

import com.aashdit.digiverifier.common.enums.ContentCategory;
import com.aashdit.digiverifier.common.enums.ContentSubCategory;
import com.aashdit.digiverifier.common.enums.ContentType;
import com.aashdit.digiverifier.common.enums.FileType;
import lombok.Data;

import java.io.File;
import java.util.Date;

@Data
public class ContentDTO {

	private Long contentId;
	
	private Long candidateId;
	
	private String bucketName;
	
	private String path;
	
	private FileType fileType;
	
	private ContentType contentType;

	private ContentCategory contentCategory;
	
	private ContentSubCategory contentSubCategory;
	
	private Date createdOn;
	
	private Date lastUpdatedOn;
	
	private File file;
	
	private String fileUrl;
	
	private String candidateCode;
}
