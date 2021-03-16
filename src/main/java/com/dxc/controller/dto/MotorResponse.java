package com.dxc.controller.dto;

import java.util.Date;

public class MotorResponse {
	private String coverNote;

	private Date inceptionDate;

	private Date expiryDate;

	private String clientSecurityNumber;

	private String engineNo;

	private String chassisNo;

	private String vehicleRegistrationNo;

	private String billingCurrency;

	private double sumInsured;

	private double rate;

	public MotorResponse() {
		super();
	}

	public MotorResponse(String coverNote, Date inception, Date expiry, String clientSecurityNumber, String engineNo,
			String chassisNo, String vehicleRegistration, String billingCurrency, double sumInsured, double rate) {
		super();
		this.coverNote = coverNote;
		this.inceptionDate = inception;
		this.expiryDate = expiry;
		this.clientSecurityNumber = clientSecurityNumber;
		this.engineNo = engineNo;
		this.chassisNo = chassisNo;
		this.vehicleRegistrationNo = vehicleRegistration;
		this.billingCurrency = billingCurrency;
		this.sumInsured = sumInsured;
		this.rate = rate;
	}

	public String getCoverNote() {
		return coverNote;
	}

	public void setCoverNote(String coverNote) {
		this.coverNote = coverNote;
	}

	public Date getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getClientSecurityNumber() {
		return clientSecurityNumber;
	}

	public void setClientSecurityNumber(String clientSecurityNumber) {
		this.clientSecurityNumber = clientSecurityNumber;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getVehicleRegistrationNo() {
		return vehicleRegistrationNo;
	}

	public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
		this.vehicleRegistrationNo = vehicleRegistrationNo;
	}

	public String getBillingCurrency() {
		return billingCurrency;
	}

	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
