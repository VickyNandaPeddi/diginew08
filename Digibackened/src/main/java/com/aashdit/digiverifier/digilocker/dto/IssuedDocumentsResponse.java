package com.aashdit.digiverifier.digilocker.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class IssuedDocumentsResponse implements Serializable{

	
	private static final long serialVersionUID = -8771556550427023422L;
	
	private String name;
	private String type;
	private String size;
	private String date;
	private String parent;
	private List<String> mime;
	private String uri;
	private String doctype;
	private String description;
	private String issuerid;
	private String issuer;
	
//	public IssuedDocumentsResponse() {
//		
//	}
//	
//	public IssuedDocumentsResponse(String name, String type, String size, String date, String parent, String mime,
//			String uri, String doctype, String description, String issuerid, String issuer) {
//		this.name = name;
//		this.type = type;
//		this.size = size;
//		this.date = date;
//		this.parent = parent;
//		this.mime = mime;
//		this.uri = uri;
//		this.doctype = doctype;
//		this.description = description;
//		this.issuerid = issuerid;
//		this.issuer = issuer;
//	}
//
//	
//	
//
//	@Override
//	public String toString() {
//		return "IssuedDocumentsResponse [name=" + name + ", type=" + type + ", size=" + size + ", date=" + date
//				+ ", parent=" + parent + ", mime=" + mime + ", uri=" + uri + ", doctype=" + doctype + ", description="
//				+ description + ", issuerid=" + issuerid + ", issuer=" + issuer + "]";
//	}
	
}
