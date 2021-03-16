package com.dxc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dxc.model.Policy;

public class PolicyDaoImpl implements PolicyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Policy add(Policy p) {
		String sql = "insert into dbo.ContractDetail(policy_no,cover_note,annual_prenium,posted_prenium,"
				+ "status,policy_owner) " + "values (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, p.getPolicyNo(), p.getCoverNote().getCoverNote(), p.getAnnualPremium(),
				p.getPostedPremium(), p.getStatus(), p.getPolicyOwner());

		return p;

	}
}
