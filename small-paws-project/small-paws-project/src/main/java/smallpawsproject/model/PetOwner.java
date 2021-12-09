package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

  @OneToMany(mappedBy = "userId")
  private List<AdoptionRequest> myRequests;


  public PetOwner(){}


  @JsonCreator
  public PetOwner(@JsonProperty("jobTitle") String jobTitle, @JsonProperty("address") String address,@JsonProperty("zipcode") int zipcode,  @JsonProperty("age") int age,@JsonProperty("avgIncome") int avgIncome, @JsonProperty("familyStatus") String familyStatus, @JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("sex") String sex,
      @JsonProperty("id") Integer id, @JsonProperty("userName") String userName, @JsonProperty("password") String password, @JsonProperty("email") String email, @JsonProperty("role") String role)
  {
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



  public void setAdress(String adress)
  {
    this.address = adress;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

//  public List<AdoptionRequest> getMyRequests() {
//    return myRequests;
//  }
//
//  public void setMyRequests(List<AdoptionRequest> myRequests) {
//    this.myRequests = myRequests;
//  }
}
