package Small.Paws.smallpawsdata.dataaccess;

import Small.Paws.smallpawsdata.model.Animal;
import Small.Paws.smallpawsdata.model.Employee;
import Small.Paws.smallpawsdata.model.PetOwner;
import Small.Paws.smallpawsdata.services.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DataAccessImpl implements DataAccess
{
  @Autowired
  private final ServiceFactory serviceFactory;

  public DataAccessImpl(ServiceFactory serviceFactory)
  {
    this.serviceFactory = serviceFactory;
  }

  @Override public void registerPetOwner(PetOwner petOwner)
  {
    serviceFactory.getPetOwnerService().registerPetOwner(petOwner);
  }

  @Override public List<Employee> getEmployees()
  {
    return null;
  }

  @Override public List<PetOwner> getPetOwners()
  {
    return null;
  }

  @Override public List<Animal> getAnimals()
  {
    return null;
  }
}
