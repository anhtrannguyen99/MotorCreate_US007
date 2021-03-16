package com.dxc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currency")
public class Currency {
	@Id
	@Column(name = "code")
	String code;

	@Column(name = "value")
	String value;

	public Currency() {

	}

	public Currency(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}