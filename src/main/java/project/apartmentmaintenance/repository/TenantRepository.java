package project.apartmentmaintenance.repository;

import project.apartmentmaintenance.model.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant,String> {
}
