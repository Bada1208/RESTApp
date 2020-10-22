package com.sysoiev.rest.controller;

import com.sysoiev.rest.model.Customer;
import com.sysoiev.rest.repository.CustomerRepository;
import com.sysoiev.rest.repository.impl.HibernateCustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomerRESTController  {
    private CustomerRepository customerRepository = new HibernateCustomerRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> printAll() {
        return customerRepository.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
        return Response.ok(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") Long index) {
        Customer customer = customerRepository.getById(index);
        customerRepository.deleteById(index);
        if (customer != null) {
            return Response.ok(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateCustomer(@PathParam("id") Long index, Customer updateCustomer) {
        updateCustomer.setId(index);
        customerRepository.update(updateCustomer);
        return Response.ok(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValueByIndex(@PathParam("id") Long index) {
        Customer customer = customerRepository.getById(index);
        if (customer != null) {
            return Response.ok(customer, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
