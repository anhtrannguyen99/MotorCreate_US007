package com.dxc.service;

import org.springframework.stereotype.Service;

import com.dxc.controller.dto.PolicyReponse;
import com.dxc.model.Policy;

@Service
public interface PolicyService {
	PolicyReponse addPolicy(Policy policy, String error);

	String createRandomPolicyNumber();
}
