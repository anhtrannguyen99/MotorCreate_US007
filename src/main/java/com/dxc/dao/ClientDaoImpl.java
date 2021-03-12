package com.dxc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dxc.model.Client;

public class ClientDaoImpl implements ClientDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Client> findClientId (String clientId) {
		String sql = String.format("select * from dbo.Client where client_id = '%s'", clientId);
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Client>(Client.class));
	}
	
	

}