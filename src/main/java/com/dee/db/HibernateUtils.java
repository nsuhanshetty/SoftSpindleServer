package com.dee.db;

/**
 * Created by akash.v on 21/04/15.
 */
import org.hibernate.Query;

import java.util.List;

/**
 * Created by abhiramk on 06/11/14.
 */
public class HibernateUtils {

    @SuppressWarnings("unchecked")
    public static <T> List<T> castList(Query q) {
        return q.list();
    }
}