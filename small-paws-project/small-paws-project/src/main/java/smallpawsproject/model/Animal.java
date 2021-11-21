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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private byte[] picture;
  private String animalType;
  private int age;
  private String description;
  private boolean washed;
  private boolean fed;
  private boolean vaccinated;

  @JsonCreator
  public Animal(@JsonProperty("animalType") String animalType, @JsonProperty("age") int age,@JsonProperty("description") String description, @JsonProperty("picture") byte[] picture,@JsonProperty("fed") boolean fed, @JsonProperty("washed") boolean washed, @JsonProperty("vaccinated") boolean vaccinated)
  {
    this.animalType = animalType;
    this.age = age;
    this.description = description;
    this.picture = picture;
    this.washed = washed;
    this.fed = fed;
    this.vaccinated = vaccinated;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public byte[] getPicture() {
    return picture;
  }

  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

  public String getAnimalType()
  {
    return animalType;
  }

  public void setAnimalType(String typeOfAnimal)
  {
    animalType = typeOfAnimal;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public boolean isWashed() {
    return washed;
  }

  public boolean isFed() {
    return fed;
  }

  public boolean isVaccinated() {
    return vaccinated;
  }
}
