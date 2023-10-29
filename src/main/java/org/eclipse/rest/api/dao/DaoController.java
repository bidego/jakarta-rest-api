package org.eclipse.rest.api.dao;

import java.util.List;
import java.util.logging.Logger;

import com.mongodb.client.MongoClients;

import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.Query;
import dev.morphia.query.filters.Filters;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DaoController {
    @Inject
    Logger logger;
    private Datastore datastore;
    @Inject
    //@ConfigProperty(name = "deployment.stage")
    // private String deploymentStage;
    @PostConstruct
    private void init() {
        logger.info("Setting datastore");
        datastore = Morphia.createDatastore(MongoClients.create("mongodb://root:example@mongo:27017/"), "jeemongo");
        datastore.getMapper().mapPackage(Employee.class.getPackageName());
        //if (DeploymentStage.DEV.name().equalsIgnoreCase(deploymentStage)) {
        //    datastore.getDatabase().drop();
        //}
        datastore.ensureIndexes();
    }
    public void save(Employee entity) {
        datastore.save(entity);
    }
    public List<Employee> getAll() {
        
        final Query<Employee> query = datastore.find(Employee.class);
        final List<Employee> employees = query.iterator().toList();
        return employees;
    }

    public List<Employee> find(String name) {
        
        final Query<Employee> query = datastore.find(Employee.class)
            .filter(Filters.eq("firstName", name));
        final List<Employee> employees = query.iterator().toList();
        return employees;
    }
}
