package com.dxc.dao;

import java.util.List;

import com.dxc.model.Motor;

public interface MotorDao {
	List<Motor> findAllMotor();
	void addContract(Motor motor);
	int getCombineEngineAndChassisNumber(Motor motor);
	int findCoverNote (String coverNote);
}
