package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
@Transactional
@DynamicUpdate
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
  @Column(columnDefinition = "TEXT")
  @Lob
  private String description;
  private boolean washed;
  private boolean fed;
  private boolean vaccinated;

  @JsonCreator
  public Animal(@JsonProperty("animalType") String animalType, @JsonProperty("age") int age,@JsonProperty("description") String description, @JsonProperty("picture") byte[] picture,@JsonProperty("fed") boolean fed, @JsonProperty("washed") boolean washed, @JsonProperty("vaccinated") boolean vaccinated)
  {
    this.picture = picture;
    this.washed = washed;
    this.fed = fed;
    this.vaccinated = vaccinated;
    this.animalType = animalType;
    this.age = age;
    this.description = description;
  }


  public void set(int age, String description, boolean washed, boolean fed, boolean vaccinated){
    this.age = age;
    this.description = description;
    this.washed = washed;
    this.fed = fed;
    this.vaccinated = vaccinated;
  }


  public boolean isWashed() {
    return washed;
  }

  public void setWashed(boolean washed) {
    this.washed = washed;
  }

  public boolean isFed() {
    return fed;
  }

  public void setFed(boolean fed) {
    this.fed = fed;
  }

  public boolean isVaccinated() {
    return vaccinated;
  }

  public void setVaccinated(boolean vaccinated) {
    this.vaccinated = vaccinated;
  }

  public Animal(){}

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getAnimalType()
  {
    return animalType;
  }

  public void setAnimalType(String animalType)
  {
    this.animalType = animalType;
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

  public byte[] getPicture() {
    return picture;
  }

  public void setPicture(byte[] picture) {
    this.picture = picture;
  }

}
