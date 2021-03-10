package com.dxc.dao;

import java.util.List;

import com.dxc.model.MotorModel;

public interface MotorDao {
	List<MotorModel> findAllMotor();
	void addContract(MotorModel motor);
	List<String> getCombineEngineAndChassisNumber();
}
