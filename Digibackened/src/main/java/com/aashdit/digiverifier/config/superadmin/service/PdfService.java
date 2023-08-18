package com.aashdit.digiverifier.config.superadmin.service;

import com.aashdit.digiverifier.config.candidate.dto.CandidateReportDTO;
import com.aashdit.digiverifier.vendorcheck.dto.ConventionalCandidateReportDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;

public interface PdfService {
    public String parseThymeleafTemplate(String templateName, CandidateReportDTO variable);

    public String parseThymeleafTemplateForConventionalCandidate(String templateName, ConventionalCandidateReportDto variable);

    public void generatePdfFromHtml(String html, File report);
}
