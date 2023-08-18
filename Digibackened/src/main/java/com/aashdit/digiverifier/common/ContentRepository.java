package com.aashdit.digiverifier.common;

import com.aashdit.digiverifier.common.enums.ContentCategory;
import com.aashdit.digiverifier.common.enums.ContentSubCategory;
import com.aashdit.digiverifier.common.enums.ContentType;
import com.aashdit.digiverifier.common.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByCandidateIdAndContentTypeIn(Long candidateId, List<ContentType> type);

    Optional<Content> findByCandidateIdAndContentTypeAndContentCategoryAndContentSubCategory(Long candidateId, ContentType contentType,
                                                                                             ContentCategory contentCategory, ContentSubCategory contentSubCategory);

    Optional<Content> findByContentId(Long contentId);

    List<Content> findAllByCandidateId(Long candidateId);

    @Query("SELECT ct FROM Content ct WHERE ct.candidateId = ?1 AND ct.createdOn = (SELECT MAX(ct2.createdOn) FROM Content ct2 WHERE ct2.candidateId = ?1)")
    Content findByCandidateIdAndCreatedOn(Long candidateId);

}

