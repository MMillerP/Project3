package project.apartmentmaintenance.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import project.apartmentmaintenance.model.Tenant;

import java.util.Collection;


@Repository
public class TenantSearchRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Collection searchTenants(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(Criteria.where("tenantID").regex(text, "i"))
        ), Tenant.class);
    }
}
