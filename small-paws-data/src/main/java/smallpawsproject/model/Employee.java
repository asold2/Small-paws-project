package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import smallpawsproject.repositories.AccountsRepository;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Employees")
@Entity
public class Employee implements Serializable
{

  @Id
  @Column(name="User_id")
  @GeneratedValue(strategy = GenerationType.TABLE)
  private int id;
  private role role;
  private String userName;
  private String password;

  public enum role{Veterinarian, AnimalAttendant}

  @OneToOne(cascade = CascadeType.ALL)
  private Account account;

  public Employee(){};

  @JsonCreator
  public Employee(String userName, String password, role userRole)
  {

    this.userName = userName;
    this.password = password;
    this.role = userRole;
//    this.account = new Account(id, userName, password);
  }

  public role getRole()
  {
    return role;
  }

  public void setRole(role role)
  {
    this.role = role;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
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

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  //  public String getUserName()
//  {
//    return userName;
//  }
//
//  public void setUserName(String userName)
//  {
//    this.userName = userName;
//  }
//
//  public String getPassword()
//  {
//    return password;
//  }
//
//  public void setPassword(String password)
//  {
//    this.password = password;
//  }
}
