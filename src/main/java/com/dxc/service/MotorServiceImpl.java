package com.dxc.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.controller.dto.MotorRequest;
import com.dxc.dao.ClientDao;
import com.dxc.dao.CurrencyDao;
import com.dxc.dao.ErrorMessageDao;
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

	private ErrorMessageDao errorMessage;

	@Autowired
	public void setErrorMessage(ErrorMessageDao errorMessage) {
		this.errorMessage = errorMessage;
	}

	private CurrencyDao currencyDao;

	@Autowired
	public void setCurrenctDao(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	@Override
	public StringBuilder checkInput(MotorRequest motorRequest) {
		StringBuilder errorString = new StringBuilder("");

		// cover
		if (!"".equals(motorRequest.getCoverNote())) {
			if (motorDao.findCoverNote(motorRequest.getCoverNote()) > 0) {
				errorString.append(", ").append(errorMessage.getError("cover existed"));

			}
		} else if ("".equals(motorRequest.getCoverNote()) || motorRequest.getCoverNote() == null) {
			errorString.append(", ").append(errorMessage.getError("cover null"));
		}

		// date
		if (motorRequest.getInceptionDate() == null) {
			errorString.append(", ").append(errorMessage.getError("inception null"));
		}

		if (motorRequest.getExpiryDate() == null) {
			errorString.append(", ").append(errorMessage.getError("expiry null"));

		} else if (daysBetween(motorRequest.getInceptionDate(), motorRequest.getExpiryDate()) != 0) {
			if (motorRequest.getInceptionDate().after(motorRequest.getExpiryDate())) {
				errorString.append(", ").append(errorMessage.getError("inception > expiry"));
			}
		}
		// client id
		if ("".equals(motorRequest.getClientSecurityNumber()) || motorRequest.getClientSecurityNumber() == null) {
			errorString.append(", ").append(errorMessage.getError("client null"));
		} else if (clientDao.findClientId(motorRequest.getClientSecurityNumber()).size() == 0) {
			errorString.append(", ").append(errorMessage.getError("client not existed"));
		}

		// engineNo
		if ("".equals(motorRequest.getEngineNo()) || motorRequest.getEngineNo() == null) {
			errorString.append(", ").append(errorMessage.getError("engine null"));
		}

		// chassisNo
		if ("".equals(motorRequest.getChassisNo()) || motorRequest.getChassisNo() == null) {
			errorString.append(", ").append(errorMessage.getError("chassis null"));
		}

		if (motorDao.getCombineEngineAndChassisNumber(motorMapper.toEntity(motorRequest)) > 0)
			errorString.append(", ").append(errorMessage.getError("enigine and chassis existed"));

		// vehicleNo
		if ("".equals(motorRequest.getVehicleRegistrationNo()) || motorRequest.getVehicleRegistrationNo() == null) {
			errorString.append(", ").append(errorMessage.getError("vehicle null"));
		}

		// billing currency
		if ("".equals(motorRequest.getBillingCurrency()) || motorRequest.getBillingCurrency() == null) {
			errorString.append(", ").append(errorMessage.getError("billing null"));
		} else if (currencyDao.getCurrency(motorRequest.getBillingCurrency()).size() == 0) {
			errorString.append(", ").append(errorMessage.getError("billing not existed"));
		}

		// sum
		if (motorRequest.getSumInsured() == 0.0) {
			errorString.append(", ").append(errorMessage.getError("sum insured null"));

		} else if (motorRequest.getSumInsured() < 0) {
			errorString.append(", ").append(errorMessage.getError("sum insured nagative"));
		}

		//rate
		if (motorRequest.getRate() == 0.0) {
			errorString.append(", ").append(errorMessage.getError("rate null"));
		} else if (motorRequest.getRate() < 0) {
			errorString.append(", ").append(errorMessage.getError("rate nagative"));

		}

		return errorString;
	}

	@Override
	public boolean add(MotorRequest motorRequest) {

		if ("".equals(checkInput(motorRequest)) || checkInput(motorRequest) == null) {
			return false;
		} else {
			motorDao.addContract(motorMapper.toEntity(motorRequest));
			return true;
		}

	}

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
