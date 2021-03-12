package com.dxc.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.dao.MotorDao;
import com.dxc.model.MotorModel;

public class MotorServiceImpl implements MotorService {

	private MotorDao motorDao;

	@Autowired
	public void setMotorDao(MotorDao motorDao) {
		this.motorDao = motorDao;
	}
	
	

	// method
	@Override
	public StringBuilder checkInput(MotorModel motor) {

		StringBuilder errorString = new StringBuilder("");

		// ngay null
		if (motor.getInceptionDate() == null) {
			errorString.append("Inception date is required");
		}

		if (motor.getExpiryDate() == null) {
			errorString.append(", Expiry date is required");

			// ngay bat dau == ngay ket thuc
		} else if (daysBetween(motor.getInceptionDate(), motor.getExpiryDate()) != 0) { // dư thừa, xets affter rồi ==0
			if (motor.getInceptionDate().after(motor.getExpiryDate())) {
				errorString.append(", Expiry date must > Inception date");
			}
		}

		// bắt buộc
		if ("".equals(motor.getClientSecurityNumber()) || motor.getClientSecurityNumber() == null) {
			errorString.append(", Client security number is required");
		}

		if ("".equals(motor.getEngineNo()) || motor.getEngineNo() == null) {
			errorString.append(", Engine number is required");
		}

		if ("".equals(motor.getChassisNo()) || motor.getChassisNo() == null) {
			errorString.append(", Chassis number is required");
		}

		// kết hợp getChassisNo & getEngineNo
		if (motorDao.getCombineEngineAndChassisNumber(motor) > 0)
			errorString.append(", This Combination of Engine number and Chassis number is already existed");

//		if (!"".equals(motor.getChassisNo()) && !"".equals(motor.getEngineNo())) {
//			for (String s : motorDao.getCombineEngineAndChassisNumber()) {
//				if ((motor.getEngineNo().concat(motor.getChassisNo())).equals(s)) {
//				}
//			}
//			if (combineCheck == true) {
//				errorString.append(", This Combination of Engine number and Chassis number is already existed");
//			}
//		}

		if ("".equals(motor.getVehicleRegistrationNo())) {
			errorString.append(", Vehicle registation number is required");
		}

		if ("".equals(motor.getBillingCurrency())) {
			errorString.append(", Billing currency is required");
		}

		if (motor.getSumInsured() == 0.0) {
			errorString.append(", Sum insured is required");

		} else if (motor.getSumInsured() < 0) {
			errorString.append(", Sum insured can't be nagative");
		}

		if (motor.getRate() == 0.0) {
			errorString.append(", Rate is required");
		} else if (motor.getRate() < 0) {
			errorString.append(", Rate can't be nagative");
		}

		return errorString;
	}

	@Override
	public boolean add(MotorModel motor) {

		if (checkInput(motor).equals("") || checkInput(motor) == null) {
			return false;
		} else {
			motorDao.addContract(motor);
			return true;
		}

	}

	
	
	
	// ngay
	private LocalDate convertToLocalDate(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@SuppressWarnings("unused")
	public double daysBetween(Date d1, Date d2) {
		if (d1 != null && d2 != null) {
			LocalDate ld1 = convertToLocalDate(d1);
			LocalDate ld2 = convertToLocalDate(d2);
			double days = ChronoUnit.DAYS.between(ld1, ld2);
			return days;
		}
		return 0;
	}

}
