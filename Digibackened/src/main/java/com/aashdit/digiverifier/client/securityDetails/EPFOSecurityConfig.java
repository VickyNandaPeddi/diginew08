package com.aashdit.digiverifier.client.securityDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="com.dgv.client")
@Configuration
public class EPFOSecurityConfig {
	
	@Value("${com.dgv.client.credential.epfo.clientid}")
	private String clientId;
	
	
	@Value("${com.dgv.client.credential.epfo.clientsecret}")
	private String clientSecret;

	
	@Value("${com.dgv.client.access.epfo.token.url}")
	private String accessTokenUrl;
	
	
	@Value("${com.dgv.client.access.epfo.transactionid.url}")
	private String transactionIdUrl;
	
	
	@Value("${com.dgv.client.credential.epfo.clientid.value}")
	private String clientIdValue;
	
	
	@Value("${com.dgv.client.credential.epfo.clientid.secret.value}")
	private String clientSecretValue;	
	
	
	@Value("${com.dgv.client.access.epfo.post.loginpage.session.url}")
	private String loginPageSessionUrl;

	@Value("${com.dgv.client.access.epfo.post.submit.post.url}")
	private String finalSubmitPostUrl;
	
	@Value("${com.dgv.client.access.epfo.captcha.image.path}")
	private String epfoCaptchaImagePath;
	
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @return the accessTokenUrl
	 */
	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	/**
	 * @return the transactionIdUrl
	 */
	public String getTransactionIdUrl() {
		return transactionIdUrl;
	}

	/**
	 * @return the clientIdValue
	 */
	public String getClientIdValue() {
		return clientIdValue;
	}

	/**
	 * @return the clientSecretValue
	 */
	public String getClientSecretValue() {
		return clientSecretValue;
	}

	/**
	 * @return the loginPageSessionUrl
	 */
	public String getLoginPageSessionUrl() {
		return loginPageSessionUrl;
	}

	
	
	/**
	 * @return the finalSubmitPostUrl
	 */
	public String getFinalSubmitPostUrl() {
		return finalSubmitPostUrl;
	}

	/**
	 * @return the epfoCaptchaImagePath
	 */
	public String getEpfoCaptchaImagePath() {
		return epfoCaptchaImagePath;
	}
	
}


	