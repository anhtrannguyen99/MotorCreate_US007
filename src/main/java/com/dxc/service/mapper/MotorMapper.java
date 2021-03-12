package com.dxc.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.controller.dto.MotorRequest;
import com.dxc.dao.ClientDao;
import com.dxc.model.Motor;

@Component
public class MotorMapper {

	ClientDao clientDao;

	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public Motor toEntity (MotorRequest motorRequest) {
		Motor motor = new Motor();
		motor.setCoverNote(motorRequest.getCoverNote());
		motor.setBillingCurrency(motorRequest.getBillingCurrency());
		motor.setChassisNo(motorRequest.getChassisNo());
		
		
		
		if(clientDao.findClientId(motorRequest.getClientSecurityNumber()).size() > 0)
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
