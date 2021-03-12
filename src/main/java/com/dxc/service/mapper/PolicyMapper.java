package com.dxc.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.model.MotorModel;
import com.dxc.model.Policy;
import com.dxc.service.MotorService;
import com.dxc.service.PolicyService;

@Component
public class PolicyMapper {

	private MotorService motorService;

	@Autowired
	public void setMotorService(MotorService motorService) {
		this.motorService = motorService;
	}

	private PolicyService policyService;

	@Autowired
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public Policy toReponse(MotorModel motor) {
		double days = motorService.daysBetween(motor.getInceptionDate(), motor.getExpiryDate());
		double annualPremium = (motor.getSumInsured() * motor.getRate()) / 100;
		double postedPremium = annualPremium * (days / 365);

		Policy p = new Policy();

		p.setPolicyNo(policyService.createRandomPolicyNumber());
		p.setCoverNote(motor);
		p.setAnnualPremium(annualPremium);
		p.setPostedPremium(postedPremium);// chua tinh , can tinh so ngay giua Inceptiondate va Expirydate
		p.setPolicyOwner(motor.getClientSecurityNumber());

		StringBuilder error = motorService.checkInput(motor);
		if (!"".equals(String.valueOf(error))) {
			p.setStatus("P");
		} else {
			p.setStatus("S");
		}
		
		p.setError(String.valueOf(error));

		return p;
	}

}