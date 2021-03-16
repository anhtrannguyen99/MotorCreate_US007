package com.dxc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Contract")
public class Motor {

	@Id
	@Column(name = "cover_note")
	private String coverNote;

	@Column(name = "inception_date")
	private Date inceptionDate;

	@Column(name = "expiry_date")
	private Date expiryDate;

	@Column(name = "client_security_number")
	private Client clientSecurityNumber;

	@Column(name = "engine_no")
	private String engineNo;

	@Column(name = "chassis_no")
	private String chassisNo;

	@Column(name = "vehicle_registration_no")
	private String vehicleRegistrationNo;

	@Column(name = "billing_currency")
	private Currency billingCurrency;

	@Column(name = "sum_insured")
	private double sumInsured;

	@Column(name = "rate")
	private double rate;

	public Motor() {
		super();
	}

	public Motor(String coverNote, Date inception, Date expiry, Client clientSecurityNumber, String engineNo,
			String chassisNo, String vehicleRegistration, Currency billingCurrency, double sumInsured, double rate) {
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

	public Client getClientSecurityNumber() {
		return clientSecurityNumber;
	}

	public void setClientSecurityNumber(Client clientSecurityNumber) {
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

	public Currency getBillingCurrency() {
		return billingCurrency;
	}

	public void setBillingCurrency(Currency billingCurrency) {
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