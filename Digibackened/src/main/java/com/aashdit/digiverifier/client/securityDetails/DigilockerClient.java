package com.aashdit.digiverifier.client.securityDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="com.dgv.client")
@Configuration
public class DigilockerClient {
	
	@Value("${com.dgv.client.credential.username}")
	private String username;
	
	@Value("${com.dgv.client.relation.credential.username}")
	private String relationUsername;
	
	@Value("${com.dgv.client.credential.password}")
	private String password;
	
	@Value("${com.dgv.client.relation.credential.password}")
	private String relationPassword;
	
	@Value("${com.dgv.client.authorization}")
	private String authorization;
	
	@Value("${com.dgv.client.authorize.type}")
	private String authorizationType;
	
	@Value("${com.dgv.client.grant.type}")
	private String grantType;
	
	@Value("${com.dgv.client.redirect.uri}")
	private String redirectUri;
	
	@Value("${com.dgv.client.relation.redirect.uri}")
	private String relationRedirectUri;
	
	@Value("${com.dgv.client.access.token.url}")
	private String accessTokenUrl;
	
	@Value("${com.dgv.client.access.code.uri}")
	private String accessCodeUri;
	
	@Value("${com.dgv.client.response.type}")
	private String responseType;
	
	@Value("${com.dgv.client.access.code.redirect_uri}")
	private String accessCodeRedirectUri;
	
	@Value("${com.dgv.client.relation.access.code.redirect_uri}")
	private String accessCodeRelationRedirectUri;
	
	@Value("${com.dgv.client.access.user.detail.api}")
	private String userDetailsApi;
	
	@Value("${com.dgv.client.access.user.files.issued.api}")
	private String userFilesIssued;
	
	@Value("${com.dgv.client.access.user.file.from.uri.api}")
	private String userFileFromUri;
	
	@Value("${com.dgv.client.access.user.file.from.uri.pdf}")
	private String userFileFromUriAsPdf;

	@Value("${com.dgv.client.access.digi.post.submit.otp.url}")
	private String finalSubmitPostOtp;

	@Value("${com.dgv.client.access.digi.post.aadhar.submit.post.url}")
	private String finalSubmitPostUrl;

	@Value("${com.dgv.client.access.digi.transactionid.url}")
	private String transactionIdUrl;

	@Value("${com.dgv.client.newaccess.token.url}")
	private String newaccessTokenUrl;

	@Value("${com.dgv.client.digi.document.post.url}")
	private String documrntSubmitPostUrl;
	
	private String code;
	
	private String accessToken;
	
	private String state;

	@Value("${com.dgv.client.vendor.check.url}")
	private String vendorChecksUrl;

	public String getVendorChecksUrl() {
		return vendorChecksUrl;
	}

	public String getdocumrntSubmitPostUrl() {
		return documrntSubmitPostUrl;
	}
	
	public String getFinalSubmitPostOtp() {
		return finalSubmitPostOtp;
	}
	public String getFinalSubmitPostUrl() {
		return finalSubmitPostUrl;
	}

	public String getNewAccessTokenUrl() {
		return newaccessTokenUrl;
	}

	public String getTransactionIdUrl() {
		return transactionIdUrl;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public String getAuthorizationType() {
		return authorizationType;
	}
	public void setAuthorizationType(String authorizationType) {
		this.authorizationType = authorizationType;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}
	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}
	public String getAccessCodeUri() {
		return accessCodeUri;
	}
	public void setAccessCodeUri(String accessCodeUri) {
		this.accessCodeUri = accessCodeUri;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getAccessCodeRedirectUri() {
		return accessCodeRedirectUri;
	}
	public void setAccessCodeRedirectUri(String accessCodeRedirectUri) {
		this.accessCodeRedirectUri = accessCodeRedirectUri;
	}
	public String getUserDetailsApi() {
		return userDetailsApi;
	}
	public void setUserDetailsApi(String userDetailsApi) {
		this.userDetailsApi = userDetailsApi;
	}
	public String getUserFilesIssued() {
		return userFilesIssued;
	}
	public void setUserFilesIssued(String userFilesIssued) {
		this.userFilesIssued = userFilesIssued;
	}
	public String getUserFileFromUri() {
		return userFileFromUri;
	}
	public void setUserFileFromUri(String userFileFromUri) {
		this.userFileFromUri = userFileFromUri;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserFileFromUriAsPdf() {
		return userFileFromUriAsPdf;
	}
	public void setUserFileFromUriAsPdf(String userFileFromUriAsPdf) {
		this.userFileFromUriAsPdf = userFileFromUriAsPdf;
	}
	public String getRelationUsername() {
		return relationUsername;
	}
	public void setRelationUsername(String relationUsername) {
		this.relationUsername = relationUsername;
	}
	public String getRelationPassword() {
		return relationPassword;
	}
	public void setRelationPassword(String relationPassword) {
		this.relationPassword = relationPassword;
	}
	public String getRelationRedirectUri() {
		return relationRedirectUri;
	}
	public void setRelationRedirectUri(String relationRedirectUri) {
		this.relationRedirectUri = relationRedirectUri;
	}
	public String getAccessCodeRelationRedirectUri() {
		return accessCodeRelationRedirectUri;
	}
	public void setAccessCodeRelationRedirectUri(String accessCodeRelationRedirectUri) {
		this.accessCodeRelationRedirectUri = accessCodeRelationRedirectUri;
	}
}
