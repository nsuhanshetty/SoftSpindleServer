package com.dee.db.implementaion;

import com.dee.OMS.models.Order;
import com.dee.db.HibernateUtils;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by akash.v on 10/05/15.
 */
public class OrderDAO extends AbstractDAO<Order>{
    private Order order;
    private List<Order> orders;
    private OrderItemsDAO orderItemsDAO;

    @Inject
    public OrderDAO(SessionFactory sessionFactory,OrderItemsDAO orderItemsDAO) {
        super(sessionFactory);
        this.orderItemsDAO=orderItemsDAO;
    }

    public Order getOrderFromStore(long id) {
        currentSession().getSessionFactory().openSession();
        Query query = currentSession().createQuery("from Order where id = :ordId");
        query.setParameter("ordId",id);
        orders = HibernateUtils.castList(query);
        orders.get(0).setOrderItems(orderItemsDAO.getOrderItemsFromStore(orders.get(0).getOrderId()));
        return orders.get(0);
    }

    public Order updatePayment(long id,float amount){
        order = getOrderFromStore(id);
        if(order != null){
            order.setCurrentPayment(order.getCurrentPayment()+amount);
            saveOrUpdate(order);
        }
        return order;
    }

    public Order saveOrUpdate(Order order) {
        order.setItemIds();
        orderItemsDAO.saveOrUpdate(order.getOrderItems());
        return persist(order);
    }

}
