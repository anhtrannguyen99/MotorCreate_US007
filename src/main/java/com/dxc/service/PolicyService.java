package com.dxc.service;

import org.springframework.stereotype.Service;

import com.dxc.controller.dto.PolicyResponse;
import com.dxc.model.Policy;

@Service
public interface PolicyService {
	PolicyResponse addPolicy(Policy policy, String error);

	String createRandomPolicyNumber();
}
