package com.dxc.dao;

import java.util.List;

import com.dxc.model.Currency;

public interface CurrencyDao{
	List<Currency> getCurrency(String code);
}