package com.dxc.service;

import org.springframework.stereotype.Service;

import com.dxc.model.MotorModel;
import com.dxc.model.Policy;

@Service
public interface PolicyService {
	Policy addPolicy(MotorModel m);
}