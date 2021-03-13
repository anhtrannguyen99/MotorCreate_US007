package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.controller.dto.MotorRequest;
import com.dxc.controller.dto.PolicyReponse;
import com.dxc.dao.MotorDao;
import com.dxc.model.Motor;
import com.dxc.model.Policy;
import com.dxc.service.MotorService;
import com.dxc.service.PolicyService;
import com.dxc.service.mapper.PolicyMapper;

@RestController
public class MotorController {

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

	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	@ResponseBody
	public int getAll() {
		return motorDao.findCoverNote("12344a");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/contracts")
	@ResponseBody
	public PolicyReponse addContract(@RequestBody MotorRequest motorRequest) {
		String error = String.valueOf(motorService.checkInput(motorRequest));

		if (!"".equals(error)) {
			error = error.substring(2);
			return policyMapper.toResponse(policyMapper.toPolicy(motorRequest), error);
		} else {
			Policy policy = policyMapper.toPolicy(motorRequest);
			motorService.add(motorRequest);
			return policyService.addPolicy(policy, error);
		}

	}

}
