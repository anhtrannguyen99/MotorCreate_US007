package com.dxc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.controller.dto.MotorResponse;
import com.dxc.controller.dto.MotorRequest;
import com.dxc.controller.dto.PolicyResponse;
import com.dxc.dao.MotorDao;
import com.dxc.model.Policy;
import com.dxc.service.MotorService;
import com.dxc.service.PolicyService;
import com.dxc.service.mapper.PolicyMapper;

@RestController
@RequestMapping("/contracts")
public class MotorController {
	private static final Logger LOGGER = LogManager.getLogger(MotorController.class);

	MotorService motorService;

	PolicyService policyService;

	PolicyMapper policyMapper;

	@Autowired
	MotorDao motorDao;

	@Autowired
	public void setMotorService(MotorService motorService) {
		this.motorService = motorService;
	}

	@Autowired
	public void setPolicyMapper(PolicyMapper policyMapper) {
		this.policyMapper = policyMapper;
	}

	@Autowired
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<MotorResponse> getAll() {
		return motorDao.findAllMotor();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public PolicyResponse addContract(@RequestBody MotorRequest motorRequest) {
		String error = String.valueOf(motorService.checkInput(motorRequest));
		LOGGER.info(motorRequest);
		if (!"".equals(error)) {
			error = error.substring(2);
			LOGGER.error(error);
			return policyMapper.toResponse(policyMapper.toPolicy(motorRequest), error);
		} else {
			Policy policy = policyMapper.toPolicy(motorRequest);
			motorService.add(motorRequest);
			System.err.println(policy.getPolicyNo() + policy.getPolicyOwner() + policy.getPostedPremium() + policy.getAnnualPremium() + policy.getCoverNote());
			LOGGER.info(policy);
			return policyService.addPolicy(policy, error);
		}

	}

}
