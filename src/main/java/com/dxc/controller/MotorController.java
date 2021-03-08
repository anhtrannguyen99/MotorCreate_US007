package com.dxc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.dao.MotorDao;
import com.dxc.model.MotorModel;

@RestController
public class MotorController {

	@Autowired
	MotorDao motorDao;
	
	List<String> list = new ArrayList<String>();
	

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<MotorModel> getAll() {
		return motorDao.findAllMotor();
	}

}
		