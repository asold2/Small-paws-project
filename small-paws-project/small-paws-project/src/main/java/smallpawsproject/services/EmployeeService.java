package smallpawsproject.services;

import smallpawsproject.model.Employee;

public interface EmployeeService
{
  String authenticateEmployee(String username, String password);
}
