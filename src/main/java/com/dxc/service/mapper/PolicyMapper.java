package com.dxc.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.controller.dto.MotorRequest;
import com.dxc.controller.dto.PolicyReponse;
import com.dxc.dao.ClientDao;
import com.dxc.model.Motor;
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

	private MotorMapper motorMapper;

	@Autowired
	public void setMotorMapper(MotorMapper motorMapper) {
		this.motorMapper = motorMapper;
	}

	private ClientDao clientDao;

	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public Policy toPolicy(MotorRequest motorRequest) {
		double days = motorService.daysBetween(motorRequest.getInceptionDate(), motorRequest.getExpiryDate());
		double annualPremium = (motorRequest.getSumInsured() * motorRequest.getRate()) / 100;
		double postedPremium = annualPremium * (days / 365);

		Policy p = new Policy();

		Motor motor = motorMapper.toEntity(motorRequest);

		p.setPolicyNo(policyService.createRandomPolicyNumber());

		p.setAnnualPremium(annualPremium);
		p.setPostedPremium(postedPremium);
		p.setCoverNote(motor);

		if (clientDao.findClientId(motorRequest.getClientSecurityNumber()).size() > 0)
			p.setPolicyOwner(String.format("%s %s", motor.getClientSecurityNumber().getFirstName(),
					motor.getClientSecurityNumber().getLastName()));

		StringBuilder error = motorService.checkInput(motorRequest);
		if (!"".equals(String.valueOf(error))) {
			p.setStatus("F");
		} else {
			p.setStatus("S");
		}

		return p;
	}

	public PolicyReponse toResponse(Policy policy, String error) {

		PolicyReponse policyReponse = new PolicyReponse();

		policyReponse.setCoverNote(policy.getCoverNote().getCoverNote());
		policyReponse.setAnnualPremium(policy.getAnnualPremium());
		policyReponse.setPolicyNo(policy.getPolicyNo());
		policyReponse.setPostedPremium(policy.getPostedPremium());
		policyReponse.setPolicyOwner(policy.getPolicyOwner());
		policyReponse.setStatus(policy.getStatus());
		policyReponse.setError(error);
		return policyReponse;

	}
}