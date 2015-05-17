package com.dee;

import com.dee.OMS.models.Order;
import com.dee.OMS.models.OrderItem;
import com.dee.customer.models.Customer;
import com.dee.customer.models.Staff;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

/**
 * Created by akash.v on 16/04/15.
 */
public class DeeModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public HibernateBundle providesHibernateBundle() {
        return new HibernateBundle<DeeConfiguration>(
                Customer.class,
                Order.class,
                OrderItem.class,
                Staff.class

        ) {
            @Override
            public DataSourceFactory getDataSourceFactory(DeeConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };
    }

    @Provides
    public SessionFactory providesSessionFactory(HibernateBundle bundle) {
        return bundle.getSessionFactory();
    }


}
