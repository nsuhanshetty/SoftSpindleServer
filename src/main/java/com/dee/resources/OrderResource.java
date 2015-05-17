package com.dee.resources;

import com.dee.OMS.models.Order;
import com.dee.db.implementaion.OrderDAO;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by akash.v on 10/05/15.
 */
@Path("/order")
@Slf4j
@Singleton
@Transactional
public class OrderResource {

    private OrderDAO orderDAO;

    @Inject
    public OrderResource(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Path("/create")
    @UnitOfWork
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order){
        orderDAO.saveOrUpdate(order);
        return Response.status(Response.Status.CREATED).entity(order).build();//(orderDAO.saveOrUpdate(order));
    }

    @Path("/payment/add/{id}")
    @UnitOfWork
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order updatePayment(@PathParam("id") long id, long amount){
        return orderDAO.updatePayment(id,amount);
    }

}
