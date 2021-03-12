package com.dxc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dxc.model.Motor;

public class MotorDaoImpl implements MotorDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Motor> findAllMotor() {
		String sql = "SELECT * FROM Motor";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Motor>(Motor.class));
	}

	@Override
	public void addContract(Motor motor) {
		String sql = "insert into dbo.Motor(cover_note,inception_date,expiry_date,client_security_number,engine_no,chassis_no,"
				+ "vehicle_registration_no,billing_currency,sum_insured,rate) " + "values (?,?,?,?,?,?,?,?,?,?)";
		if ("".equals(motor.getChassisNo())) {
			return;
		} else {
			jdbcTemplate.update(sql, motor.getCoverNote(), motor.getInceptionDate(), motor.getExpiryDate(),
					motor.getClientSecurityNumber().getClientId(), motor.getEngineNo(), motor.getChassisNo(),
					motor.getVehicleRegistrationNo(), motor.getBillingCurrency(), motor.getSumInsured(),
					motor.getRate());
		}

	}

	@Override
	public List<Motor> findCoverNote(String coverNote) {
		String sql = String.format("select * from dbo.Motor where cover_note = '%s'", coverNote);

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Motor>(Motor.class));
	}

	@Override
	public int getCombineEngineAndChassisNumber(Motor motor) {
//		String sql = "select concat(engine_no,chassis_no) from dbo.Motor";
//		return jdbcTemplate.queryForList(sql,String.class);

		String sql = String.format("select count(*) from dbo.Motor where engine_no = '%s' AND chassis_no = '%s'",
				motor.getEngineNo(), motor.getChassisNo());
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
