package com.dxc.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.dao.MotorDao;
import com.dxc.dao.PolicyDao;
import com.dxc.model.MotorModel;
import com.dxc.model.Policy;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyDao pd;

	@Autowired
	MotorDao motorDao;
	
	@Override
	public Policy addPolicy(MotorModel m) {
		
		Policy p = new Policy();
		double days = daysBetween(m.getInceptionDate(), m.getExpiryDate());
		double annualPremium = (m.getSumInsured() * m.getRate()) / 100;
		double postedPremium = annualPremium * (days/365);
		StringBuilder errorString = new StringBuilder();
		boolean errorCheck = false;
		Date checkDate = null;
		boolean nullCheck = false;
		boolean combineCheck = false;
		
		List<String> listEngineCombineChassis = motorDao.getCombineEngineAndChassisNumber();
		
		
		p.setPolicyNo(createRandomPolicyNumber());
		p.setCoverNote(m.getCoverNote());
		p.setAnnualPremium(annualPremium);
		p.setPostedPremium(postedPremium);// chua tinh , can tinh so ngay giua Inceptiondate va Expirydate
		p.setPolicyOwner(m.getClientSecurityNumber());
		
		
		if (m.getInceptionDate()==checkDate){
			errorString.append("Inception date is required");
			errorCheck = true;
			nullCheck = true;
		}
		if (m.getExpiryDate()==checkDate){
			errorString.append(", Expiry date is required");
			errorCheck = true;
			nullCheck = true;
		}else if (days != 0) {
			if (m.getInceptionDate().after(m.getExpiryDate())) {
				errorString.append(", Expiry date must > Inception date");
				errorCheck = true;
			}
		}
		if ("".equals(m.getClientSecurityNumber())){
			errorString.append(", Client security number is required");
			errorCheck = true;
			nullCheck = true;
		}
		if ("".equals(m.getEngineNo())){
			errorString.append(", Engine number is required");
			errorCheck = true;
			nullCheck = true;
		}
		if ("".equals(m.getChassisNo())){
			errorString.append(", Chassis number is required");
			errorCheck = true;
			nullCheck = true;
		}
		if(!"".equals(m.getChassisNo()) && !"".equals(m.getEngineNo())) {		
			for(String s : listEngineCombineChassis) {
				if((m.getEngineNo().concat(m.getChassisNo())).equals(s)) {
					combineCheck = true;
					errorCheck = true;
				}
			}
			if(combineCheck == true) {
				errorString.append(", This Combination of Engine number and Chassis number is already existed");
			}
		}
		if ("".equals(m.getVehicleRegistrationNo())){
			errorString.append(", Vehicle registation number is required");
			errorCheck = true;
			nullCheck = true;
		}
		if ("".equals(m.getBillingCurrency())){
			errorString.append(", Billing currency is required");
			errorCheck = true;
			nullCheck = true;
		}
		if (m.getSumInsured() == 0.0){
			errorString.append(", Sum insured is required");
			errorCheck = true;
			nullCheck = true;
		}else if (m.getSumInsured() < 0) {
			errorString.append(", Sum insured can't be nagative");
			errorCheck = true;
		}
		if (m.getRate() == 0.0){
			errorString.append(", Rate is required");
			errorCheck = true;
			nullCheck = true;
		}else if (m.getRate() < 0) {
			errorString.append(", Rate can't be nagative");
			errorCheck = true;
		}

		if (errorCheck == true) {
			p.setStatus("F");
		}else {
			p.setStatus("S");
		}
		p.setError(errorString.toString());
		if(nullCheck == false){
			pd.addPolicy(p);
		}
		return p;
	}

	private String createRandomPolicyNumber() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z' , change to 57 if only want random number
		int targetStringLength = 8;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	private LocalDate convertToLocalDate(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	private double daysBetween(Date d1, Date d2) {
		if(d1!=null && d2 != null) {
			LocalDate ld1 = convertToLocalDate(d1);
			LocalDate ld2 = convertToLocalDate(d2);
			final double days = ChronoUnit.DAYS.between(ld1, ld2);
			return days;
		}
		return 0;
	}
	
}


























