package com.aashdit.digiverifier.client.securityDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="com.dgv.client")
@Configuration
public class ITRSecurityConfig {
	
	@Value("${com.dgv.client.credential.itr.clientid}")
	private String clientId;
	
	
	@Value("${com.dgv.client.credential.itr.clientsecret}")
	private String clientSecret;

	
	@Value("${com.dgv.client.access.itr.token.url}")
	private String accessTokenUrl;
	
	
	@Value("${com.dgv.client.access.itr.transactionid.url}")
	private String transactionIdUrl;
	
	
	@Value("${com.dgv.client.credential.itr.clientid.value}")
	private String clientIdValue;
	
	
	@Value("${com.dgv.client.credential.itr.clientid.secret.value}")
	private String clientSecretValue;	
	
	
	@Value("${com.dgv.client.access.itr.post.logininfo.url}")
	private String postLoginInfoUrl;
	
	

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
	 * @return the postLoginInfoUrl
	 */
	public String getPostLoginInfoUrl() {
		return postLoginInfoUrl;
	}
}


	