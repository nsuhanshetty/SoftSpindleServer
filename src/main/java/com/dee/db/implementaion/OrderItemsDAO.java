package com.dee.db.implementaion;

import com.dee.OMS.models.OrderItem;
import com.dee.db.HibernateUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by akash.v on 12/05/15.
 */
@Singleton
public class OrderItemsDAO extends AbstractDAO<OrderItem> {
    @Inject
    public OrderItemsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<OrderItem> getOrderItemsFromStore(long orderId) {
        currentSession().getSessionFactory().openSession();
        Query query = currentSession().createQuery("");
        query.setParameter("ordId",orderId);
        return HibernateUtils.castList(query);
    }

    public void saveOrUpdate(List<OrderItem> orderItems) {
        for(OrderItem oi: orderItems) {
            persist(oi);
        }
    }
}
