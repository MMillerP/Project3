package project.apartmentmaintenance.repository;

import org.springframework.stereotype.Repository;
import project.apartmentmaintenance.model.Tenant;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TenantRepository extends CrudRepository<Tenant,String> {
}
