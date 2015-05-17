package com.dee;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by akash.v on 16/04/15.
 */
public class DeeService extends Application<DeeConfiguration> {
    public static void main(String[] args) throws Exception {
        new DeeService().run(args);
    }


    private final MigrationsBundle<DeeConfiguration> migrationsBundle =
            new MigrationsBundle<DeeConfiguration>() {
                @Override
                public DataSourceFactory getDataSourceFactory(DeeConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    private final GuiceBundle<DeeConfiguration> guiceBundle =
            GuiceBundle.<DeeConfiguration>newBuilder()
                    .addModule(new DeeModule())
                    .enableAutoConfig(getClass().getPackage().getName())
                    .setConfigClass(DeeConfiguration.class)
                                        .build();




    @Override
    public void initialize(Bootstrap<DeeConfiguration> bootstrap) {
        bootstrap.addBundle(migrationsBundle);
        bootstrap.addBundle(guiceBundle);
        HibernateBundle hibernateBundle = guiceBundle.getInjector().getInstance(HibernateBundle.class);
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle( new AssetsBundle("/get/","/"));
    }

    @Override
    public void run(DeeConfiguration configuration, Environment environment) throws Exception {


    }
}
