package com.dxc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Policy")
public class Policy {
	
	@Id
	@Column(name = "policy_no")
	private String policyNo;
	
	@Column(name = "cover_note")
	private MotorModel coverNote;
	
	@Column(name = "annual_prenium")
	private double annualPremium;
	
	@Column(name = "posted_prenium")
	private double postedPremium;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "error")
	private String error;
	
	@Column(name = "policy_owner")
	private String policyOwner;
	
	

	public Policy() {
		
	}

	public Policy(String policyNo, MotorModel coverNote, double annualPremium, double postedPremium, String status,
			String error, String policyOwner) {
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

	public MotorModel getCoverNote() {
		return coverNote;
	}

	public void setCoverNote(MotorModel coverNote) {
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




















