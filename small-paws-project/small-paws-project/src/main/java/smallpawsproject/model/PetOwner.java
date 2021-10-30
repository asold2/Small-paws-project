package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class PetOwner extends UserLogIn implements Serializable
{

  @Id
  private Long id;
  private String firstName;
  private String lastName;
  private int age;
  private String sex;
  private String familyStatus;
  private int avgIncome;
  private String address;
  private String JobTitle;
  private String userName;
  private String password;

  public PetOwner(){}


  @JsonCreator
  public PetOwner(Long id,String firstName, String lastName, int age, String sex,
      String familyStatus, int avgIncome, String address, String jobTitle,
      String userName, String password)
  {
    super(id,userName,password);
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
    this.familyStatus = familyStatus;
    this.avgIncome = avgIncome;
    this.address = address;
    JobTitle = jobTitle;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public String getSex()
  {
    return sex;
  }

  public void setSex(String sex)
  {
    this.sex = sex;
  }

  public String getFamilyStatus()
  {
    return familyStatus;
  }

  public void setFamilyStatus(String familyStatus)
  {
    this.familyStatus = familyStatus;
  }

  public int getAvgIncome()
  {
    return avgIncome;
  }

  public void setAvgIncome(int avgIncome)
  {
    this.avgIncome = avgIncome;
  }

  public String getAdress()
  {
    return address;
  }

  public void setAdress(String adress)
  {
    this.address = adress;
  }

  public String getJobTitle()
  {
    return JobTitle;
  }

  public void setJobTitle(String jobTitle)
  {
    JobTitle = jobTitle;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
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
