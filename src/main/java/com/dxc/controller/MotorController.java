package com.dxc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.dao.MotorDao;
import com.dxc.model.MotorModel;
import com.dxc.model.Policy;
import com.dxc.service.PolicyService;

@RestController
public class MotorController {

	@Autowired
	MotorDao motorDao;
	
	@Autowired
	PolicyService ps;
	
	List<String> list = new ArrayList<String>();
	

	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	@ResponseBody
	public List<MotorModel> getAll() {
		return motorDao.findAllMotor();
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/contracts")
	@ResponseBody
	public Policy addContract(@RequestBody MotorModel motor) {
		Policy p = ps.addPolicy(motor);
		if ("S".equals(p.getStatus())) {
			motorDao.addContract(motor);
		}
		return p;
	}

}
		