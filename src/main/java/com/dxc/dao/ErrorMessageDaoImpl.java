package com.dxc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ErrorMessageDaoImpl implements ErrorMessageDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public StringBuilder getError(String error) {
		String sql = String.format("select description from dbo.ErrorMessage where error = N'%s'", error);
		
		return jdbcTemplate.queryForObject(sql, StringBuilder.class);
	}

}
