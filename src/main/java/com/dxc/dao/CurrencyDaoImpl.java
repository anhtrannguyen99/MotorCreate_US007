package com.dxc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dxc.model.Currency;

public class CurrencyDaoImpl implements CurrencyDao{
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Currency> getCurrency(String code) {
		String sql = String.format("SELECT * FROM Currency Where code = '%s' ", code);
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Currency>(Currency.class));

	}

}
