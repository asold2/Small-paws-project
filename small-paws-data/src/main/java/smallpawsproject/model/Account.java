package smallpawsproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Account implements Serializable
{
  @Id
  private int id;
  private String userName;
  private String password;

//  private enum type{Employee, PetOwner}

  public Account(String userName, String password){
    this.userName = userName;
    this.password = password;
  }

  @OneToOne(optional = true)
  private PetOwner petOwner;

  @OneToOne(optional = true)
  private Employee employee;

public Account(){}

  public Account(int id ,String userName, String password)
  {
    this.id = id;
    this.userName = userName;
    this.password = password;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public PetOwner getPetOwner()
  {
    return petOwner;
  }

  public void setPetOwner(PetOwner petOwner)
  {
    this.petOwner = petOwner;
  }

  public Employee getEmployee()
  {
    return employee;
  }

  public void setEmployee(Employee employee)
  {
    this.employee = employee;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }


}
