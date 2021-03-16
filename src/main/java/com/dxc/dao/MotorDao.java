package com.dxc.dao;

import java.util.List;

import com.dxc.controller.dto.MotorResponse;
import com.dxc.model.Motor;

public interface MotorDao {
	List<MotorResponse> findAllMotor();
	void addContract(Motor motor);
	int getCombineEngineAndChassisNumber(Motor motor);
	int findCoverNote (String coverNote);
}
