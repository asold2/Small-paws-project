package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

@Entity
public class PetOwner extends EndUser
{

  private static final long serialVersionUID = 663126647076776891L;

  private Integer id;
  private String firstName;
  private String lastName;
  private int age;
  private String sex;
  private String familyStatus;
  private int avgIncome;
  private String address;
  private int zipcode;
  private String jobTitle;

  @OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
  private List<AdoptionRequest> myRequests;



  public PetOwner(){}


  public PetOwner(String jobTitle, String address,int zipcode, int age, int avgIncome, String familyStatus, String firstName, String lastName, String sex, Integer id,String userName, String password, String email, String role){
    super(userName, password, email, role);
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
    this.familyStatus = familyStatus;
    this.avgIncome = avgIncome;
    this.address = address;
    this.zipcode = zipcode;
    this.jobTitle = jobTitle;
    myRequests = new ArrayList<>();

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

  public Integer getId()
  {
    return id;
  }

  public void Integer(int id)
  {
    this.id = id;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }


  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }

  public List<AdoptionRequest> getMyRequests() {
    return myRequests;
  }

  public void setMyRequests(List<AdoptionRequest> myRequests) {
    this.myRequests = myRequests;
  }
}
