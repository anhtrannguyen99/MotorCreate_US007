package com.dxc.dao;

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

	
}
