package project.apartmentmaintenance.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import project.apartmentmaintenance.model.Request;

import java.util.Collection;
import java.util.List;

@Repository
public class RequestSearchRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Collection searchRequest(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(Criteria.where("RequestID").regex(text, "i"),
                        Criteria.where("apartNum").regex(text, "i"),
                        Criteria.where("areaProblem").regex(text, "i"))
        ), Request.class);
    }

    public List<Request> sortANumQueryASC() {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "apartNum"));
        return mongoTemplate.find(query,Request.class);
    }
    public List<Request> sortANumQueryDESC() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "apartNum"));
        return mongoTemplate.find(query,Request.class);
    }

    public List<Request> sortAProblemQueryASC() {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "areaProblem"));
        return mongoTemplate.find(query,Request.class);
    }
    public List<Request> sortAProblemQueryDESC() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "areaProblem"));
        return mongoTemplate.find(query,Request.class);
    }

    public List<Request> sortDateTimeQueryASC() {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "reqTime"));
        return mongoTemplate.find(query,Request.class);
    }
    public List<Request> sortDateTimeQueryDESC() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "reqTime"));
        return mongoTemplate.find(query,Request.class);
    }

    public List<Request> sortStatusQueryASC() {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "status"));
        return mongoTemplate.find(query,Request.class);
    }
    public List<Request> sortStatusQueryDESC() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "status"));
        return mongoTemplate.find(query,Request.class);
    }



}
