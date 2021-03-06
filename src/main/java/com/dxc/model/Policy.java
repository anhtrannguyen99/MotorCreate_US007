package com.dxc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ContractDetail")
public class Policy {
	
	@Id
	@Column(name = "policy_no")
	private String policyNo;
	
	@Column(name = "cover_note")
	private Motor coverNote;
	
	@Column(name = "annual_prenium")
	private double annualPremium;
	
	@Column(name = "posted_prenium")
	private double postedPremium;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "policy_owner")
	private String policyOwner;
	
	

	public Policy() {
		
	}

	public Policy(String policyNo, Motor coverNote, double annualPremium, double postedPremium, String status,
			String error, String policyOwner) {
		super();
		this.policyNo = policyNo;
		this.coverNote = coverNote;
		this.annualPremium = annualPremium;
		this.postedPremium = postedPremium;
		this.status = status;
		this.policyOwner = policyOwner;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Motor getCoverNote() {
		return coverNote;
	}

	public void setCoverNote(Motor coverNote) {
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

	public String getPolicyOwner() {
		return policyOwner;
	}

	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}
	
	
}




















