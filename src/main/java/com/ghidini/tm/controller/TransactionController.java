package com.ghidini.tm.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ghidini.tm.domain.dto.TransactionDTO;
import com.ghidini.tm.exceptions.IdNotFoundException;
import com.ghidini.tm.service.TransactionService;

@Path("transaction")
public class TransactionController {
	
	@Inject
	private TransactionService service;

	@POST
	@Path("/do")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response doTransaction(TransactionDTO transaction) {
		Response response = null;
		try {
			service.addTransaction(transaction);
			response = Response.status(Response.Status.CREATED).build();
		} catch (IdNotFoundException id) {
			response = Response.status(Response.Status.NOT_FOUND).entity(id.getMessage()).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTransactions() {
		Response response = null;
		try {
			List<TransactionDTO> allTransactions = service.getAllTransaction();
			response = Response.status(Response.Status.OK).entity(allTransactions).build();
		} catch (IdNotFoundException id) {
			response = Response.status(Response.Status.NOT_FOUND).entity(id.getMessage()).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

}
