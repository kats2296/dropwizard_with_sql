package com.kuliza.dropwizard_with_sql;

import com.kuliza.dropwizard_with_sql.resource.EmployeeResource;
import com.kuliza.dropwizard_with_sql.service.EmployeeService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import org.skife.jdbi.v2.DBI;


public class DropWizardMySqlApp extends Application<DropwizardMySqlConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DropWizardMySqlApp.class);
    private static final String SQL = "sql";

    public static void main(String[] args) throws Exception {
        new DropWizardMySqlApp().run("server", args[0]);
    }

    @Override
    public void initialize(Bootstrap<DropwizardMySqlConfiguration> b) {
    }

    @Override
    public void run(DropwizardMySqlConfiguration config, Environment env)
            throws Exception {
        final DataSource dataSource =
                config.getDataSourceFactory().build(env.metrics(), SQL);
        DBI dbi = new DBI(dataSource);

        logger.info("Registering RESTful API resources");
        env.jersey().register(new EmployeeResource(dbi.onDemand(EmployeeService.class)));
    }
}