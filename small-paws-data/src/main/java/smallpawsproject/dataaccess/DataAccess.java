package smallpawsproject.dataaccess;

import smallpawsproject.model.Account;
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
  List<Account> getAccounts();
}
