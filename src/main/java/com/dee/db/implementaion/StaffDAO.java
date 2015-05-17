package com.dee.db.implementaion;

import com.dee.customer.models.Staff;
import com.dee.db.HibernateUtils;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by akash.v on 05/05/15.
 */

public class StaffDAO extends AbstractDAO<Staff> {
    @Inject
    public StaffDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Staff> getStaffFromStore(String name,String mobile,String phone) {

        if(name==null) name = "";
        if(mobile==null) mobile = "";
        if(phone==null) phone = "";

        Query query = currentSession().createQuery("from Staff where (name like :name and mobileNo like :num and phoneNo like :phnum)");
        query.setParameter("name","%"+name+"%");
        query.setParameter("num","%"+mobile+"%");
        query.setParameter("phnum","%"+phone+"%");

        return HibernateUtils.castList(query);
    }

    public Staff saveOrUpdate(Staff staff) {
        //log.info("profile {}", profile);
        List<Staff> profileList = getStaffFromStore(staff.getName(), staff.getMobileNo(), staff.getPhoneNo());
        if(profileList.isEmpty()) {
            return persist(staff);
        } else {
            Staff staff1= profileList.get(0);
            staff.setId(staff1.getId());

            return persist(staff);
        }
    }


}
