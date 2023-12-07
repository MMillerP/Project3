package project.apartmentmaintenance.repository;

import org.springframework.stereotype.Repository;
import project.apartmentmaintenance.model.Request;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RequestRepository extends CrudRepository<Request,String> {

}
