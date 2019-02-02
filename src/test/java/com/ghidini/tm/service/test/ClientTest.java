package com.ghidini.tm.service.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ghidini.tm.dao.interfaces.IClientDAO;
import com.ghidini.tm.domain.Client;
import com.ghidini.tm.domain.dto.ClientDTO;
import com.ghidini.tm.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {

	@InjectMocks
	private ClientService service;
	
	@Mock
	private IClientDAO clientDao;

	@Before
	public void init() {
		Mockito.when(clientDao.findById(1L)).thenReturn(new Client(1L,"Rafael"));
		Mockito.when(clientDao.findAll()).thenReturn(Arrays.asList(new Client(1L,"Rafael")));
	}

	@Test
	public void findClientById() {

		Client client = new Client(1L,"Rafael");
		service.addClient(new Client("Rafael"));
		ClientDTO clientDto = service.findClientById(1L);
		assertTrue(Objects.equals(client.getClientName(), clientDto.getClientName()));

	}
	
	@Test
	public void findAll() {

		List<ClientDTO> listClientsDto = service.getAllClients();
		assertTrue(CollectionUtils.isNotEmpty(listClientsDto));

	}
	
	@Test
	public void deleteClientById() {
		
		verify(clientDao, times(0)).delete(1L);
		
	}
	
	@Test
	public void addClient() {
		
		verify(clientDao, times(0)).insert(new Client("Rafael"));
		
	}
	
	@Test
	public void updateClient() {
		
		verify(clientDao, times(0)).update(new Client(1L, "Rafael"));
		
	}

}
