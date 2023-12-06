package project.apartmentmaintenance.repository;

import project.apartmentmaintenance.model.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request,String> {

}
