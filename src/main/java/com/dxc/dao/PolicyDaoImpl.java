package com.dxc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dxc.model.Policy;

@Repository
public class PolicyDaoImpl implements PolicyDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addPolicy(Policy p) {
		String sql = "insert into dbo.Policy(policy_no,cover_note,annual_prenium,posted_prenium,"
				+"status,error,policy_owner) "
				+ "values (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,
				p.getPolicyNo(),
				p.getCoverNote(),
				p.getAnnualPremium(),
				p.getPostedPremium(),
				p.getStatus(),
				p.getError(),
				p.getPolicyOwner());
		
	}
}
