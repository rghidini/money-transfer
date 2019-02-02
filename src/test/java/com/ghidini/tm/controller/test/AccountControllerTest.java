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
public class AccountControllerTest {
	
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
	public void addAccount201() {
		String json = "{\"clientName\":\"Rafael Ghidini\"}";
		Response response = target.path("client/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
		
		json = "{\"clientId\":1, \"amount\":100}";
		response = target.path("account/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}
	@Test
	public void addAccount500() {
		String json = "{\"clientName\":\"Rafael Ghidini\"}";
		Response response = target.path("client/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
		
		json = "{\"clientId\":a, \"amount\":100}";
		response = target.path("account/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR_500.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void deleteClient200() {
		addAccount201();
		Response response = target.path("account/delete/1")
				.request()
				.delete();

		assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
	}
	@Test
	public void deleteClient404() {
		Response response = target.path("account/delete/30")
				.request()
				.delete();

		assertEquals(HttpStatus.NOT_FOUND_404.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void getAccountById302() {
		addAccount201();
		Response response = target.path("account/2")
				.request()
				.get();

		assertEquals(HttpStatus.FOUND_302.getStatusCode(), response.getStatus());
	}
	@Test
	public void getAccountById404() {
		Response response = target.path("account/30")
				.request()
				.get();

		assertEquals(HttpStatus.NOT_FOUND_404.getStatusCode(), response.getStatus());
	}

	@Test
	public void getAllAccounts200() {
		addAccount201();
		Response response = target.path("account/")
				.request()
				.get();

		assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
	}

}
