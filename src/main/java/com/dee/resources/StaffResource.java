package com.dee.resources;

import com.dee.customer.models.Staff;
import com.dee.db.implementaion.StaffDAO;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.sun.jersey.multipart.FormDataParam;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * Created by akash.v on 26/12/14.
 */


@Transactional
@Path("/staff")
@Slf4j
@Singleton
public class StaffResource {
    private StaffDAO staffDAO;
    private Staff st;
    File file;

    @Inject
    public StaffResource(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    @POST
    @UnitOfWork
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Staff staff) throws ParseException {
        staffDAO.saveOrUpdate(staff);
        return Response.status(Response.Status.CREATED).build();//customerDAO.saveOrUpdate(customer);
    }

    @GET
    @UnitOfWork
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Staff> fetchCustomersFromPhone(@QueryParam("name") String name,@QueryParam("mobile") String mobile,@QueryParam("phone") String phone){
        return staffDAO.getStaffFromStore(name, mobile, phone);
    }

    @POST
    @Path("/upload/image")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") final InputStream stream,@FormDataParam("mobile") final String mobile) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(new File(mobile));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = stream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        System.out.println("Done!");

    }
    @GET
    @Path("/get/image/{mobile}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFile(@PathParam("mobile") String mobile) {

        file = new File("./"+mobile);

        if(mobile.isEmpty()||mobile==null)
            return Response.status(405).entity("provide mobile number").build();

        if(!file.canRead())
            return Response.status(404).entity("Customer pic not found").build();

        return Response.ok(file).build();
    }

}