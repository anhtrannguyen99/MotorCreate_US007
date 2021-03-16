package com.dxc.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.controller.dto.MotorRequest;
import com.dxc.dao.ClientDao;
import com.dxc.dao.CurrencyDao;
import com.dxc.model.Motor;

@Component
public class MotorMapper {

	ClientDao clientDao;

	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	CurrencyDao currencyDao;

	@Autowired
	public void setCurrencyDao(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

	public Motor toEntity(MotorRequest motorRequest) {
		Motor motor = new Motor();
		motor.setCoverNote(motorRequest.getCoverNote());
		if (currencyDao.getCurrency(motorRequest.getBillingCurrency()).size() > 0) {
			motor.setBillingCurrency(currencyDao.getCurrency(motorRequest.getBillingCurrency()).get(0));
		}
		motor.setChassisNo(motorRequest.getChassisNo());

		if (clientDao.findClientId(motorRequest.getClientSecurityNumber()).size() > 0)
			motor.setClientSecurityNumber(clientDao.findClientId(motorRequest.getClientSecurityNumber()).get(0));

		motor.setEngineNo(motorRequest.getEngineNo());

		motor.setExpiryDate(motorRequest.getExpiryDate());
		motor.setInceptionDate(motorRequest.getInceptionDate());
		motor.setSumInsured(motorRequest.getSumInsured());
		motor.setVehicleRegistrationNo(motorRequest.getVehicleRegistrationNo());

		motor.setRate(motorRequest.getRate());
		return motor;
	}

}
