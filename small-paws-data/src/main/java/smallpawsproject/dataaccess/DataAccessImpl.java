package smallpawsproject.dataaccess;

import smallpawsproject.model.EndUser;
import smallpawsproject.model.Animal;
import smallpawsproject.model.PetOwner;
import smallpawsproject.services.ServiceFactory;
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


  @Override public List<PetOwner> getPetOwners()
  {
    return serviceFactory.getPetOwnerService().getPetOwners();
  }

  @Override public List<Animal> getAnimals()
  {
    return null;
  }

  @Override public List<EndUser> getUsers()
  {
    return serviceFactory.getUserService().getUsers();

  }
}
