package com.dxc.controller.dto;

public class PolicyReponse {
	private String policyNo;

	private String coverNote;

	private double annualPremium;

	private double postedPremium;

	private String status;

	private String error;

	private String policyOwner;
	
	
	public PolicyReponse() {
		super();
	}

	public PolicyReponse(String policyNo, String coverNote, double annualPremium, double postedPremium,
			String status, String error, String policyOwner) {
		super();
		this.policyNo = policyNo;
		this.coverNote = coverNote;
		this.annualPremium = annualPremium;
		this.postedPremium = postedPremium;
		this.status = status;
		this.error = error;
		this.policyOwner = policyOwner;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getCoverNote() {
		return coverNote;
	}

	public void setCoverNote(String coverNote) {
		this.coverNote = coverNote;
	}

	public double getAnnualPremium() {
		return annualPremium;
	}

	public void setAnnualPremium(double annualPremium) {
		this.annualPremium = annualPremium;
	}

	public double getPostedPremium() {
		return postedPremium;
	}

	public void setPostedPremium(double postedPremium) {
		this.postedPremium = postedPremium;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}
}
