package com.dee.db.implementaion;

import com.dee.customer.models.Customer;
import com.dee.db.HibernateUtils;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by akash.v on 05/05/15.
 */

public class CustomerDAO extends AbstractDAO<Customer> {
    @Inject
    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Customer> getCustomerFromStore(String name,String mobile,String phone) {

        if(name==null) name = "";
        if(mobile==null) mobile = "";
        if(phone==null) phone = "";

        Query query = currentSession().createQuery("from Customer where (name like :name and mobileNo like :num and phoneNo like :phnum)");
        query.setParameter("name","%"+name+"%");
        query.setParameter("num","%"+mobile+"%");
        query.setParameter("phnum","%"+phone+"%");

        return HibernateUtils.castList(query);
    }

    public Customer saveOrUpdate(Customer customer) {
        //log.info("profile {}", profile);
        List<Customer> profileList = getCustomerFromStore(customer.getName(),customer.getMobileNo(),customer.getPhoneNo());
        if(profileList.isEmpty()) {
            return persist(customer);
        } else {
            Customer customer1= profileList.get(0);
            customer.setId(customer1.getId());

            return persist(customer);
        }
    }


}
