package com.dxc.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.controller.dto.PolicyResponse;
import com.dxc.dao.PolicyDao;
import com.dxc.model.Policy;
import com.dxc.service.mapper.PolicyMapper;

@Service
public class PolicyServiceImpl implements PolicyService {

	private PolicyDao policyDao;

	@Autowired
	public void setPolicyDao(PolicyDao policyDao) {
		this.policyDao = policyDao;
	}

	private PolicyMapper policyMapper;

	@Autowired
	public void setPolicyMapper(PolicyMapper policyMapper) {
		this.policyMapper = policyMapper;
	}

	
	
	//method
	@Override
	public PolicyResponse addPolicy(Policy policy, String error) {

		return policyMapper.toResponse(policyDao.add(policy), error);
	}
	
	
	

	@Override
	public String createRandomPolicyNumber() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z' , change to 57 if only want random number
		int targetStringLength = 8;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

}
