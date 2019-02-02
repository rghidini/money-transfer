package com.ghidini.tm.controller.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ghidini.tm.main.Main;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionControllerTest {

	private HttpServer server;
	private WebTarget target;


	@Before
	public void setUp() {

		server = Main.startServer();

		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() {
		server.shutdownNow();
	}

	@Test
	public void doTransaction201() {
		String json;
		Response response;
		
		createFirstClient200();
		
		createSecondClient200();
		
		createFirstAccount200();
		
		createSecondAccount200();
		
		json = "{\"fromAccountId\":1, \"toAccountId\":2, \"amount\":90}";
		response = target.path("transaction/do")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}

	@Test
	public void doTransaction404() {
		String json;
		Response response;

		createFirstClient200();
		
		createSecondClient200();
		
		createFirstAccount200();
		
		createSecondAccount200();
		
		json = "{\"fromAccountId\":2, \"toAccountId\":30, \"amount\":5}";
		response = target.path("transaction/do")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.NOT_FOUND_404.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void doTransaction500() {
		String json;
		Response response;

		createFirstClient200();
		
		createSecondClient200();
		
		createFirstAccount200();
		
		createSecondAccount200();
		
		json = "{\"fromAccountId\":2, \"toAccountId\":1, \"amount\":-90}";
		response = target.path("transaction/do")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR_500.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void getAllTransactions200() {
		Response response = target.path("transaction/")
				.request()
				.get();

		assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
	}
	
	private void createSecondAccount200() {
		String json;
		Response response;
		json = "{\"clientId\":2, \"amount\":100}";
		response = target.path("account/add")
				.request()
				.post(Entity.json(json));
		
		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}

	private void createFirstAccount200() {
		String json;
		Response response;
		json = "{\"clientId\":1, \"amount\":100}";
		response = target.path("account/add")
				.request()
				.post(Entity.json(json));
		
		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}

	private void createSecondClient200() {
		String json;
		Response response;
		json = "{\"clientName\":\"Laura Goncalves\"}";
		response = target.path("client/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}

	private void createFirstClient200() {
		String json = "{\"clientName\":\"Rafael Ghidini\"}";
		Response response = target.path("client/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}

}
