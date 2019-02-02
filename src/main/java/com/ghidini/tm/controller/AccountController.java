package com.ghidini.tm.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ghidini.tm.domain.Account;
import com.ghidini.tm.domain.dto.AccountDTO;
import com.ghidini.tm.exceptions.IdNotFoundException;
import com.ghidini.tm.service.AccountService;

@Path("account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {
	
	private static final Logger logger = Logger.getLogger(AccountController.class);

	private AccountService service = new AccountService();

	@POST
	@Path("/add")
	public Response addAccount(Account account) {
		Response response = null;
		try {
			service.addAccount(account);
			response = Response.status(Response.Status.CREATED).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@DELETE
	@Path("/delete/{id}")
	public Response deleteAccount(@PathParam(value = "id") Long id) {
		Response response = null;
		try {
			service.deleteAccount(id);
			response = Response.status(Response.Status.OK).build();
		} catch (IdNotFoundException e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("/{id}")
	public Response getAccountById(@PathParam(value = "id") Long id) {
		Response response = null;
		try {
			AccountDTO account = service.findAccountById(id);
			response = Response.status(Response.Status.FOUND).entity(account).build();
		} catch (IdNotFoundException e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

	@GET
	@Path("/")
	public Response getAllAccounts() {
		Response response = null;
		try {
			List<AccountDTO> allAccounts= service.getAllAccounts();
			response = Response.status(Response.Status.OK).entity(allAccounts).build();
		} catch (IdNotFoundException id) {
			logger.error(id.getMessage());
			response = Response.status(Response.Status.NOT_FOUND).entity(id.getMessage()).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return response;
	}

}
