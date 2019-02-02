package com.ghidini.tm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jvnet.hk2.annotations.Service;

import com.ghidini.tm.dao.ClientDAO;
import com.ghidini.tm.domain.Client;
import com.ghidini.tm.domain.dto.ClientDTO;
import com.ghidini.tm.exceptions.IdNotFoundException;
import com.ghidini.tm.service.interfaces.IClientService;

@Service
public class ClientService implements IClientService{

	private static final String CLIENT_NOT_FOUND = "Client not found";
	private static final String ID_MUST_BE_A_NUMBER = "Client id can't be null and must be a positive number";
	private static final String NAME_CANT_BE_NULL = "Client name can't be null and can't be contains a numeric character";

	private ClientDAO clientDao = new ClientDAO();

	@Override
	public void addClient(Client client) {
		clientDao.insert(new Client(verifyName(client.getClientName())));
	}

	@Override
	public void updateClient(Long id, Client client) {
		clientDao.update(new Client(verifyClient(verifyId(id)).getClientId(), verifyName(client.getClientName())));
	}

	@Override
	public ClientDTO findClientById(Long id) {
		ClientDTO clientDto = null;
		clientDto = new ClientDTO(verifyClient(verifyId(id)).getClientName());
		return clientDto;
	}

	@Override
	public List<ClientDTO> getAllClients() {
		List<ClientDTO> listClientDto = new ArrayList<>();
		Optional.ofNullable(clientDao.findAll())
		.orElseGet(Collections::emptyList)
		.stream()
		.filter(Objects::nonNull)
		.forEach(client -> listClientDto.add(new ClientDTO(client.getClientName())));
		return listClientDto;
	}

	@Override
	public void deleteClient(Long id) {
		clientDao.delete(verifyClient(verifyId(id)).getClientId());
	}

	private String verifyName(String name) {
		return Optional.ofNullable(name)
				.filter(StringUtils::isNotBlank)
				.filter(StringUtils::isAlphaSpace)
				.orElseThrow(() -> new IllegalArgumentException(NAME_CANT_BE_NULL));
	}

	private Long verifyId(Long id) {
		return Optional.ofNullable(id)
				.filter(i -> NumberUtils.isNumber(i.toString()))
				.filter(i -> Objects.equals(Long.signum(i), NumberUtils.INTEGER_ONE))
				.orElseThrow(() -> new IllegalArgumentException(ID_MUST_BE_A_NUMBER));
	}

	private Client verifyClient(Long id) {
		return Optional.ofNullable(clientDao.findById(id))
				.orElseThrow(() -> new IdNotFoundException(CLIENT_NOT_FOUND));
	}

}
