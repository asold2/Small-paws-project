package smallpawsproject.dataaccess;

import smallpawsproject.model.EndUser;
import smallpawsproject.model.Animal;
import smallpawsproject.model.Employee;
import smallpawsproject.model.PetOwner;

import java.util.List;

public interface DataAccess
{
  void registerPetOwner(PetOwner petOwner);
  List<Employee> getEmployees();
  List<PetOwner> getPetOwners();
  List<Animal> getAnimals();
  List<EndUser> getUsers();
}
