package com.dxc.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.dxc.model.MotorModel;

@Service
public interface MotorService {

	StringBuilder checkInput(MotorModel motor);

	boolean add(MotorModel motor);

	double daysBetween(Date d1, Date d2);
}
