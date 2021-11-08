package Small.Paws.smallpawsdata.repositories;

import Small.Paws.smallpawsdata.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
}
