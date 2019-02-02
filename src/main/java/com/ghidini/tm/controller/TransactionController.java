package com.ghidini.tm.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ghidini.tm.domain.Transaction;
import com.ghidini.tm.domain.dto.TransactionDTO;
import com.ghidini.tm.exceptions.IdNotFoundException;
import com.ghidini.tm.service.TransactionService;

@Path("transaction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionController {
	
	private static final Logger logger = Logger.getLogger(TransactionController.class);
	
	private TransactionService service = new TransactionService();

	@POST
	@Path("/do")
	public Response doTransaction(Transaction transaction) {
		Response response = null;
		try {
			service.addTransaction(transaction);
			response = Response.status(Response.Status.CREATED).build();
		} catch (IdNotFoundException id) {
			logger.error(id.getMessage());
			response = Response.status(Response.Status.NOT_FOUND).entity(id.getMessage()).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("/")
	public Response getAllTransactions() {
		Response response = null;
		try {
			List<TransactionDTO> allTransactions = service.getAllTransaction();
			response = Response.status(Response.Status.OK).entity(allTransactions).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

}
