package com.aashdit.digiverifier.utils;

import com.aashdit.digiverifier.common.model.Content;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PdfUtil {
	
	public static void mergePdfFiles(List<InputStream> inputPdfList,
		OutputStream outputStream) {
			try{
				
				Document document = new Document();
				PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
				document.open();
				PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
//				HeaderFooterPageEvent headerFooterPageEvent = new HeaderFooterPageEvent();
//				pdfWriter.setPageEvent(headerFooterPageEvent);
				for(InputStream inputStream : inputPdfList) {
					PdfReader pdfReader = new PdfReader(inputStream);
					for(int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
						document.newPage();
						PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
						pdfContentByte.addTemplate(page, 0, 0);
					}
				}
				
				outputStream.flush();
				document.close();
				outputStream.close();
			}catch(Exception e){
				System.out.println(e);
			}
	}
	
	
}
