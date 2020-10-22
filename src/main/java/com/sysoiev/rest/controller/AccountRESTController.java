package com.sysoiev.rest.controller;

import com.sysoiev.rest.model.Account;
import com.sysoiev.rest.repository.AccountRepository;
import com.sysoiev.rest.repository.impl.HibernateAccountRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/accounts")
public class AccountRESTController  {
    private AccountRepository accountRepository = new HibernateAccountRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> printAll() {
        return accountRepository.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAccount(Account newAccount) {
        accountRepository.save(newAccount);
        return Response.ok(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteAccount(@PathParam("id") Long index) {
        Account account = accountRepository.getById(index);
        accountRepository.deleteById(index);
        if (account != null) {
            return Response.ok(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateAccount(@PathParam("id") Long index, Account updateAccount) {
        updateAccount.setId(index);
        accountRepository.update(updateAccount);
        return Response.ok(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValueByIndex(@PathParam("id") Long index) {
        Account account = accountRepository.getById(index);
        if (account != null) {
            return Response.ok(account, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

