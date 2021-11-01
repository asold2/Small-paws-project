package smallpawsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smallpawsproject.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
