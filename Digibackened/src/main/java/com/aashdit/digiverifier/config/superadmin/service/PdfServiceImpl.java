package com.aashdit.digiverifier.config.superadmin.service;

import com.aashdit.digiverifier.config.candidate.dto.CandidateReportDTO;
import com.aashdit.digiverifier.vendorcheck.dto.ConventionalCandidateReportDto;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static com.itextpdf.html2pdf.css.CssConstants.PORTRAIT;


@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private TemplateEngine templateEngine;


    public String parseThymeleafTemplate(String templateName, CandidateReportDTO variable) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        Context context = new Context();
        context.setVariable("panCardVerification", variable.getPanCardVerification());
        context.setVariable("name", variable.getName());
        context.setVariable("root", variable);
        IContext context1 = context;
        return templateEngine.process(templateName, context1);
    }

    public String parseThymeleafTemplateForConventionalCandidate(String templateName, ConventionalCandidateReportDto variable) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        Context context = new Context();
//		context.setVariable("panCardVerification", variable.getPanCardVerification());
        context.setVariable("name", variable.getName());
        context.setVariable("root", variable);
        IContext context1 = context;
        return templateEngine.process(templateName, context1);
    }

    public void generatePdfFromHtml(String html, File report) {
        try {
            OutputStream outputStream = new FileOutputStream(report);
            ConverterProperties converterProperties = new ConverterProperties();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(new PageSize(PageSize.A4));
            MediaDeviceDescription mediaDeviceDescription = new MediaDeviceDescription(MediaType.PRINT);
            converterProperties.setMediaDeviceDescription(mediaDeviceDescription);
//			pdfDocument.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderHandler());
//			pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterHandler());
            HtmlConverter.convertToPdf(html, pdfDocument, converterProperties);
            outputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
