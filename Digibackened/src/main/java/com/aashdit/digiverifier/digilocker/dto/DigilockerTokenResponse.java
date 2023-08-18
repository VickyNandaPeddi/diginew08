package com.aashdit.digiverifier.digilocker.dto;

import java.io.Serializable;

public class DigilockerTokenResponse implements Serializable{
	
	private static final long serialVersionUID = -6994818759327639604L;
	
	private String access_token;
	private String expires_in;
	private String token_type;
	private String scope;
	private String refresh_token;
	private String digilockerid;
	private String name;
	private String dob;
	private String gender;
	private String eaadhaar;
	private String reference_key;
	private String new_account;
	
	
	public DigilockerTokenResponse() {
		
	}


	/**
	 * @param access_token
	 * @param expires_in
	 * @param token_type
	 * @param scope
	 * @param refresh_token
	 * @param digilockerid
	 * @param name
	 * @param dob
	 * @param gender
	 * @param eaadhaar
	 * @param reference_key
	 * @param new_account
	 */
	public DigilockerTokenResponse(String access_token, String expires_in, String token_type, String scope,
			String refresh_token, String digilockerid, String name, String dob, String gender, String eaadhaar,
			String reference_key, String new_account) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.token_type = token_type;
		this.scope = scope;
		this.refresh_token = refresh_token;
		this.digilockerid = digilockerid;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.eaadhaar = eaadhaar;
		this.reference_key = reference_key;
		this.new_account = new_account;
	}


	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}


	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	/**
	 * @return the expires_in
	 */
	public String getExpires_in() {
		return expires_in;
	}


	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}


	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}


	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}


	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}


	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}


	/**
	 * @return the refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}


	/**
	 * @param refresh_token the refresh_token to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}


	/**
	 * @return the digilockerid
	 */
	public String getDigilockerid() {
		return digilockerid;
	}


	/**
	 * @param digilockerid the digilockerid to set
	 */
	public void setDigilockerid(String digilockerid) {
		this.digilockerid = digilockerid;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}


	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the eaadhaar
	 */
	public String getEaadhaar() {
		return eaadhaar;
	}


	/**
	 * @param eaadhaar the eaadhaar to set
	 */
	public void setEaadhaar(String eaadhaar) {
		this.eaadhaar = eaadhaar;
	}


	/**
	 * @return the reference_key
	 */
	public String getReference_key() {
		return reference_key;
	}


	/**
	 * @param reference_key the reference_key to set
	 */
	public void setReference_key(String reference_key) {
		this.reference_key = reference_key;
	}


	/**
	 * @return the new_account
	 */
	public String getNew_account() {
		return new_account;
	}


	/**
	 * @param new_account the new_account to set
	 */
	public void setNew_account(String new_account) {
		this.new_account = new_account;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "DigilockerTokenResponse [access_token=" + access_token + ", expires_in=" + expires_in + ", token_type="
				+ token_type + ", scope=" + scope + ", refresh_token=" + refresh_token + ", digilockerid="
				+ digilockerid + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", eaadhaar=" + eaadhaar
				+ ", reference_key=" + reference_key + ", new_account=" + new_account + "]";
	}
	
	
}
