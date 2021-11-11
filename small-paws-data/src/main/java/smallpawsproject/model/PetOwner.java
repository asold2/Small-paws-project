package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
@Table(name="PetOwners")
@Entity
public class PetOwner implements Serializable
{
  private static final long serialVersionUID = 663126647076776891L;


  @Id
  private int id;
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

  @OneToOne(cascade = CascadeType.ALL)
  private Account account;

  public PetOwner(){}


  @JsonCreator
  public PetOwner(@JsonProperty("id") int id,@JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("age") int age,@JsonProperty("sex") String sex,
      @JsonProperty("familyStatus") String familyStatus,@JsonProperty("avgIncome") int avgIncome,@JsonProperty("address") String address,@JsonProperty("jobTitle") String jobTitle,
      @JsonProperty("userName") String userName,@JsonProperty("password") String password)
  {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
    this.familyStatus = familyStatus;
    this.avgIncome = avgIncome;
    this.address = address;
    JobTitle = jobTitle;
    this.account = new Account(id ,userName, password);
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




  public String getJobTitle()
  {
    return JobTitle;
  }

  public void setJobTitle(String jobTitle)
  {
    JobTitle = jobTitle;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
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
