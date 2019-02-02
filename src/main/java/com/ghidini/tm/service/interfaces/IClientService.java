package com.ghidini.tm.service.interfaces;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import com.ghidini.tm.domain.Client;
import com.ghidini.tm.domain.dto.ClientDTO;

@Contract
public interface IClientService {
	
	public void addClient(Client client);
	public void updateClient(Long id, Client client);
	public void deleteClient(Long id);
	public ClientDTO findClientById(Long id);
	public List<ClientDTO> getAllClients();

}
