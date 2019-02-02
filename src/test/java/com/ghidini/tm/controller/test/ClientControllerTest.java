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
public class ClientControllerTest {

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
	public void addClient201() {
		String json = "{\"clientName\":\"Rafael Ghidini\"}";
		Response response = target.path("client/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.CREATED_201.getStatusCode(), response.getStatus());
	}
	@Test
	public void addClient500() {
		String json = "{\"clientName\":\"123\"}";
		Response response = target.path("client/add")
				.request()
				.post(Entity.json(json));

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR_500.getStatusCode(), response.getStatus());
	}

	@Test
	public void deleteClient200() {
		addClient201();
		Response response = target.path("client/delete/1")
				.request()
				.delete();

		assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
	}
	@Test
	public void deleteClient404() {
		Response response = target.path("client/delete/30")
				.request()
				.delete();

		assertEquals(HttpStatus.NOT_FOUND_404.getStatusCode(), response.getStatus());
	}

	@Test
	public void getClientById302() {
		addClient201();
		Response response = target.path("client/2")
				.request()
				.get();

		assertEquals(HttpStatus.FOUND_302.getStatusCode(), response.getStatus());
	}
	@Test
	public void getClientById404() {
		Response response = target.path("client/30")
				.request()
				.get();

		assertEquals(HttpStatus.NOT_FOUND_404.getStatusCode(), response.getStatus());
	}

	@Test
	public void getAllClients200() {
		addClient201();
		Response response = target.path("client/")
				.request()
				.get();

		assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
	}

	@Test
	public void updateClient200() {
		addClient201();
		String json = "{\"clientName\":\"Rafael Ghidini\"}";
		Response response = target.path("client/update/2")
				.request()
				.put(Entity.json(json));

		assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
	}
	@Test
	public void updateClient404() {
		String json = "{\"clientName\":\"Rafael\"}";
		Response response = target.path("client/update/60")
				.request()
				.put(Entity.json(json));

		assertEquals(HttpStatus.NOT_FOUND_404.getStatusCode(), response.getStatus());
	}
}
