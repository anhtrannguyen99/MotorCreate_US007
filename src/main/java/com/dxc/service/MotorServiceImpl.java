package com.dxc.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.controller.dto.MotorRequest;
import com.dxc.dao.ClientDao;
import com.dxc.dao.MotorDao;
import com.dxc.service.mapper.MotorMapper;

public class MotorServiceImpl implements MotorService {

	private MotorDao motorDao;

	@Autowired
	public void setMotorDao(MotorDao motorDao) {
		this.motorDao = motorDao;
	}

	private ClientDao clientDao;

	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	private MotorMapper motorMapper;

	@Autowired
	public void setMotorMapper(MotorMapper motorMapper) {
		this.motorMapper = motorMapper;
	}

	// method
	@Override
	public StringBuilder checkInput(MotorRequest motorRequest) {

		StringBuilder errorString = new StringBuilder("");

		if(motorDao.findCoverNote(motorRequest.getCoverNote()).size() > 0) {
			errorString.append("Cover note number is existed");
		}
		
		// ngay null
		if (motorRequest.getInceptionDate() == null) {
			errorString.append("Inception date is required");
		}

		if (motorRequest.getExpiryDate() == null) {
			errorString.append(", Expiry date is required");

			// ngay bat dau == ngay ket thuc
		} else if (daysBetween(motorRequest.getInceptionDate(), motorRequest.getExpiryDate()) != 0) { // dư thừa, xets
																										// affter rồi
																										// ==0
			if (motorRequest.getInceptionDate().after(motorRequest.getExpiryDate())) {
				errorString.append(", Expiry date must > Inception date");
			}
		}

		// bắt buộc
		if ("".equals(motorRequest.getClientSecurityNumber()) || motorRequest.getClientSecurityNumber() == null) {
			errorString.append(", Client security number is required");
		}

		if (clientDao.findClientId(motorRequest.getClientSecurityNumber()).size() == 0) {
			errorString.append(", Client Security Number is not exist");
		}

		if ("".equals(motorRequest.getEngineNo()) || motorRequest.getEngineNo() == null) {
			errorString.append(", Engine number is required");
		}

		if ("".equals(motorRequest.getChassisNo()) || motorRequest.getChassisNo() == null) {
			errorString.append(", Chassis number is required");
		}

		// kết hợp getChassisNo & getEngineNo
		if (motorDao.getCombineEngineAndChassisNumber(motorMapper.toEntity(motorRequest)) > 0)
			errorString.append(", This Combination of Engine number and Chassis number is already existed");


		if ("".equals(motorRequest.getVehicleRegistrationNo())) {
			errorString.append(", Vehicle registation number is required");
		}

		if ("".equals(motorRequest.getBillingCurrency())) {
			errorString.append(", Billing currency is required");
		}

		if (motorRequest.getSumInsured() == 0.0) {
			errorString.append(", Sum insured is required");

		} else if (motorRequest.getSumInsured() < 0) {
			errorString.append(", Sum insured can't be nagative");
		}

		if (motorRequest.getRate() == 0.0) {
			errorString.append(", Rate is required");
		} else if (motorRequest.getRate() < 0) {
			errorString.append(", Rate can't be nagative");
		}

		return errorString;
	}

	@Override
	public boolean add(MotorRequest motorRequest) {

		if (checkInput(motorRequest).equals("") || checkInput(motorRequest) == null) {
			return false;
		} else {
			motorDao.addContract(motorMapper.toEntity(motorRequest));
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
