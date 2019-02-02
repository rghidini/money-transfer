package com.ghidini.tm.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ghidini.tm.domain.Client;
import com.ghidini.tm.domain.dto.ClientDTO;
import com.ghidini.tm.exceptions.IdNotFoundException;
import com.ghidini.tm.service.ClientService;

@Path("client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientController {

	private ClientService service = new ClientService();

	@Path("/add")
	@POST
	public Response addClient(Client client) {
		Response response = null;
		try {
			service.addClient(client);
			response = Response.status(Response.Status.CREATED).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteClient(@PathParam(value = "id") Long id) {
		Response response = null;
		try {
			service.deleteClient(id);
			response = Response.status(Response.Status.OK).build();
		} catch (IdNotFoundException e) {
			response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("/{id}")
	public Response getClientById(@PathParam(value = "id") Long id) {
		Response response = null;
		try {
			ClientDTO client = service.findClientById(id);
			response = Response.status(Response.Status.FOUND).entity(client).build();
		} catch (IdNotFoundException e) {
			response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("/")
	public Response getAllClients() {
		Response response = null;
		try {
			List<ClientDTO> allClients = service.getAllClients();
			response = Response.status(Response.Status.OK).entity(allClients).build();
		} catch (IdNotFoundException id) {
			response = Response.status(Response.Status.NOT_FOUND).entity(id.getMessage()).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@PUT
	@Path("/update/{id}")
	public Response getUpdateClient(@PathParam(value = "id") Long id, Client client) {
		Response response = null;
		try {
			service.updateClient(id, client);
			response = Response.status(Response.Status.OK).build();
		} catch (IdNotFoundException e) {
			response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}



}
