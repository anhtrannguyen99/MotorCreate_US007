package com.dxc.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.dxc.controller.dto.MotorRequest;

@Service
public interface MotorService {

	StringBuilder checkInput(MotorRequest motorRequest);

	boolean add(MotorRequest motorRequest);

	double daysBetween(Date d1, Date d2);
}
