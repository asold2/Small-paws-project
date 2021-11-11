package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@Table(name="animals")
@Entity
@Table(name = "animal")
public class Animal implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ID;
  private String TypeOfAnimal;
  private int Age;
  private String Description;

  @JsonCreator
  public Animal(@JsonProperty("typeOfAnimal") String typeOfAnimal, @JsonProperty("age") int age,@JsonProperty("description") String description)
  {
    TypeOfAnimal = typeOfAnimal;
    Age = age;
    Description = description;
  }
  public Animal(){}

  public Long getID()
  {
    return ID;
  }

  public void setID(Long ID)
  {
    this.ID = ID;
  }

  public String getTypeOfAnimal()
  {
    return TypeOfAnimal;
  }

  public void setTypeOfAnimal(String typeOfAnimal)
  {
    TypeOfAnimal = typeOfAnimal;
  }

  public int getAge()
  {
    return Age;
  }

  public void setAge(int age)
  {
    Age = age;
  }

  public String getDescription()
  {
    return Description;
  }

  public void setDescription(String description)
  {
    Description = description;
  }
}
