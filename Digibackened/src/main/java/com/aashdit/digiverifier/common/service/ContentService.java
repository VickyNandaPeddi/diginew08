package com.aashdit.digiverifier.common.service;

import com.aashdit.digiverifier.common.dto.ContentDTO;
import com.aashdit.digiverifier.common.enums.ContentViewType;

import java.util.List;

public interface ContentService {
	ContentDTO uploadFile(ContentDTO contentDTO);
	
	String getFileUrlFromContentId(Long contentId);
	
	String getContentById(Long contentId, ContentViewType type);
	
	List<ContentDTO> getContentListByCandidateId(Long candidateId);
	
}
