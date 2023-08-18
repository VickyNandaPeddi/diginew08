package com.aashdit.digiverifier.email.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@PropertySource("classpath:configuration.properties")
@ConfigurationProperties(prefix="com.digiverifier.email")
@Configuration
@Data
public class EmailProperties {
	
	private String digiverifierForwardUrllink;
	
	private String digiverifierEmailSenderId;
	
	private String digiverifierEmailTitle;
	
	private String smtpPort;

	private String sesHost;

	private String sesUsername;

	private String sesPassword;

	private String digiverifierSenderNickName;

	private String mailTransportProtocol;

	private String mailStarttlsEnabled;

	private String mailSmtpAuth;

	private String mailContentType;
	
	private String digiverifierRelationshipUrllink;

}
