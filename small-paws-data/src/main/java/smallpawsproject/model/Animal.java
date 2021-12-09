package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

@Transactional
@DynamicUpdate
@Entity
@Table(name="animal")
public class Animal implements Serializable
{

  private static final long serialVersionUID = 663126647076776891L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer animalId;
  private byte[] picture;
  private String animalType;
  private int age;
  private String sex;
  @Column(columnDefinition = "TEXT")
  @Lob
  private String description;
  private boolean washed;
  private boolean fed;
  private boolean vaccinated;
  private String healthNotes;


  @OneToMany( mappedBy = "animalId", cascade = CascadeType.REMOVE)
  private List<AdoptionRequest> myHopes;



  @JsonCreator
  public Animal(@JsonProperty("animalType") String animalType, @JsonProperty("age") int age,  @JsonProperty("sex") String sex, @JsonProperty("description") String description, @JsonProperty("picture") byte[] picture,@JsonProperty("fed") boolean fed, @JsonProperty("washed") boolean washed, @JsonProperty("vaccinated") boolean vaccinated, @JsonProperty("healthNotes") String healthNotes)
  {
    this.animalType = animalType;
    this.age = age;
    this.sex = sex;
    this.description = description;
    this.picture = picture;
    this.washed = washed;
    this.fed = fed;
    this.vaccinated = vaccinated;
    this.healthNotes = healthNotes;
    myHopes = new ArrayList<>();
  }



  public void set(byte[] picture,int age, String sex, String description, boolean washed, boolean fed, boolean vaccinated, String healthNotes){
    this.picture = picture;
    this.age = age;
    this.sex = sex;
    this.description = description;
    this.washed = washed;
    this.fed = fed;
    this.vaccinated = vaccinated;
    this.healthNotes = healthNotes;
  }


  public String getHealthNotes() {
    return healthNotes;
  }

  public void setHealthNotes(String healthNotes) {
    this.healthNotes = healthNotes;
  }

  public String getSex()
  {
    return sex;
  }

  public void setSex(String sex)
  {
    this.sex = sex;
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

  public Integer getId()
  {
    return animalId;
  }

  public void setId(Integer id)
  {
    this.animalId = id;
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

  public List<AdoptionRequest> getMyHopes() {
    return myHopes;
  }

  public void setMyHopes(List<AdoptionRequest> myHopes) {
    this.myHopes = myHopes;
  }
}
