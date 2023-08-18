package com.aashdit.digiverifier.common.service;

import com.aashdit.digiverifier.common.ContentRepository;
import com.aashdit.digiverifier.common.dto.ContentDTO;
import com.aashdit.digiverifier.common.enums.ContentType;
import com.aashdit.digiverifier.common.enums.ContentViewType;
import com.aashdit.digiverifier.common.enums.FileType;
import com.aashdit.digiverifier.common.model.Content;
import com.aashdit.digiverifier.config.candidate.dto.CandidateCafExperienceDto;
import com.aashdit.digiverifier.utils.AwsUtils;
import com.aashdit.digiverifier.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private AwsUtils awsUtils;


    public static final String DIGIVERIFIER_DOC_BUCKET_NAME = "digiverifier-new";


    @Override
    public ContentDTO uploadFile(ContentDTO contentDTO) {
        contentDTO.setBucketName(DIGIVERIFIER_DOC_BUCKET_NAME);
        if (Objects.nonNull(contentDTO.getFile())) {
            String path = "Candidate/".concat(
                    contentDTO.getCandidateCode() + "/" + StringUtils.capitalize(contentDTO.getContentType().name()).concat("/").concat(contentDTO.getFile().getName()));
            contentDTO.setPath(path);
            String url = awsUtils.uploadFileAndGetPresignedUrl(contentDTO.getBucketName(), path,
                    contentDTO.getFile());
            contentDTO.setFileUrl(url);
        }
        Content content = contentRepository
                .findByCandidateIdAndContentTypeAndContentCategoryAndContentSubCategory(contentDTO.getContentId(),
                        contentDTO.getContentType()
                        , contentDTO.getContentCategory(), contentDTO.getContentSubCategory()).orElse(new Content());
        content.setCandidateId(contentDTO.getCandidateId());
        content.setContentCategory(contentDTO.getContentCategory());
        content.setContentSubCategory(contentDTO.getContentSubCategory());
        content.setBucketName(DIGIVERIFIER_DOC_BUCKET_NAME);
        content.setPath(contentDTO.getPath());
        content.setContentType(contentDTO.getContentType());
        content.setFileType(contentDTO.getFileType());
        Content save = contentRepository.save(content);
        contentDTO.setContentId(save.getContentId());
        contentDTO.setBucketName(DIGIVERIFIER_DOC_BUCKET_NAME);
        return contentDTO;
    }

    @Override
    public String getFileUrlFromContentId(Long contentId) {
        Content content = contentRepository.findByContentId(contentId)
                .orElseThrow(() -> new RuntimeException("no content found for given id"));
        return awsUtils.getPresignedUrl(content.getBucketName(), content.getPath());
    }

    @Override
    public String getContentById(Long contentId, ContentViewType type) {
        Content content = contentRepository.findByContentId(contentId)
                .orElseThrow(() -> new RuntimeException("no content found for given id"));
        return awsUtils.getPresignedUrl(content.getBucketName(), content.getPath());
    }

    @Override
    public List<ContentDTO> getContentListByCandidateId(Long candidateId) {
        List<Content> contents = contentRepository.findAllByCandidateId(candidateId);
        return contents.stream().map(content -> this.modelMapper.map(content, ContentDTO.class)).collect(Collectors.toList());
    }

}
