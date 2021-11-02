package smallpawsproject.services;

import smallpawsproject.model.Employee;

public interface EmployeeService
{
  int authenticateEmployee(String username, String password);
}
