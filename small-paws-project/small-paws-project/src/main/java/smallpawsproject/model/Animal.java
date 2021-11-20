package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

//@Table(name="animals")
@Entity
public class Animal implements Serializable
{
  private static final long serialVersionUID = 663126647076776891L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int ID;
  private byte[] Picture;
  private String AnimalType;
  private int Age;
  private String Description;
  private boolean Washed;
  private boolean Fed;
  private boolean Vaccinated;

  @JsonCreator
  public Animal(@JsonProperty("animalType") String animalType, @JsonProperty("age") int age,@JsonProperty("description") String description, @JsonProperty("picture") byte[] picture,@JsonProperty("fed") boolean fed, @JsonProperty("washed") boolean washed, @JsonProperty("vaccinated") boolean vaccinated)
  {
    AnimalType = animalType;
    Age = age;
    Description = description;
    Picture = picture;
    Washed = washed;
    Fed = fed;
    Vaccinated = vaccinated;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public String getTypeOfAnimal()
  {
    return AnimalType;
  }

  public void setTypeOfAnimal(String typeOfAnimal)
  {
    AnimalType = typeOfAnimal;
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
