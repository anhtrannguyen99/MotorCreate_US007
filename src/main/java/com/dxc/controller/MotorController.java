package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.model.MotorModel;
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

//	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
//	@ResponseBody
//	public List<MotorModel> getAll() {
//		return motorDao.findAllMotor();
//	}

	@RequestMapping(method = RequestMethod.POST, value = "/contracts")
	@ResponseBody
	public Policy addContract(@RequestBody MotorModel motor) {
		if (!String.valueOf(motorService.checkInput(motor)).equals("")) {
			return policyMapper.toReponse(motor);
		} else {
			motorService.add(motor);
			return policyService.addPolicy(policyMapper.toReponse(motor));
		}

//		Policy p = ps.addPolicy(motor);
//		
//		if ("S".equals(p.getStatus())) {
//			motorDao.addContract(motor);
//		}
	}

}
