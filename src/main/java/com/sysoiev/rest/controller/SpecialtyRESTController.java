package com.sysoiev.rest.controller;

import com.sysoiev.rest.model.Specialty;
import com.sysoiev.rest.repository.SpecialtyRepository;
import com.sysoiev.rest.repository.impl.HibernateSpecialtyRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/specialties")
public class SpecialtyRESTController {
    private SpecialtyRepository specialtyRepository = new HibernateSpecialtyRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Specialty> printAll() {
        return specialtyRepository.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response saveSpecialty(Specialty newSpecialty) {
        specialtyRepository.save(newSpecialty);
        return Response.ok(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteSpecialty(@PathParam("id") Long index) {
        Specialty specialty = specialtyRepository.getById(index);
        specialtyRepository.deleteById(index);
        if (specialty != null) {
            return Response.ok(Response.Status.ACCEPTED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update/{id}")
    public Response updateSpecialty(@PathParam("id") Long index, Specialty updateSpecialty) {
        updateSpecialty.setId(index);
        specialtyRepository.update(updateSpecialty);
        return Response.ok(Response.Status.ACCEPTED).build();
    }

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValueByIndex(@PathParam("id") Long index) {
        Specialty specialty = specialtyRepository.getById(index);
        if (specialty != null) {
            return Response.ok(specialty, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
