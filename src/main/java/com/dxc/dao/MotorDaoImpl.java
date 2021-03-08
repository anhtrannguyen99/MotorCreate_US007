package com.dxc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dxc.model.MotorModel;

public class MotorDaoImpl implements MotorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MotorModel> findAllMotor() {
		String sql = "SELECT * FROM Motor";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<MotorModel>(MotorModel.class));
	}

	@Override
	public void addContract(MotorModel motor) {
		String sql = "insert into dbo.Motor(cover_note,inception_date,expiry_date,client_security_number,engine_no,chassis_no,vehicle_registration_no,billing_currency,sum_insured,rate) "
				+ "values (?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,
				motor.getCoverNote(),
				motor.getInceptionDate(),
				motor.getExpiryDate(),
				motor.getClientSecurityNumber(),
				motor.getEngineNo(),
				motor.getChassisNo(),
				motor.getVehicleRegistrationNo(),
				motor.getBillingCurrency(),
				motor.getSumInsured(),
				motor.getRate());
	}

	
}
