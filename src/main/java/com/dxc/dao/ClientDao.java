package com.dxc.dao;

import java.util.List;

import com.dxc.model.Client;

public interface ClientDao {
	List<Client> findClientId(String clientId);
}
