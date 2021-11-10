package Small.Paws.smallpawsdata.dataaccess;

import Small.Paws.smallpawsdata.model.Animal;
import Small.Paws.smallpawsdata.model.Employee;
import Small.Paws.smallpawsdata.model.PetOwner;

import java.util.List;

public interface DataAccess
{
  void registerPetOwner(PetOwner petOwner);
  List<Employee> getEmployees();
  List<PetOwner> getPetOwners();
  List<Animal> getAnimals();
}
