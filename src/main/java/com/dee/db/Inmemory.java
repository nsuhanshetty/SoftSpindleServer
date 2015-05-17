package com.dee.db;

import com.dee.OMS.models.Order;
import com.dee.customer.models.Customer;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by akash.v on 13/05/15.
 */
public class Inmemory {

    public static List<Customer> customerList= Lists.newArrayList();
    public static List<Order> ordersList = Lists.newArrayList();
}

