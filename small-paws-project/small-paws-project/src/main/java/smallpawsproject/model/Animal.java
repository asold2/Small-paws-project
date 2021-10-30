package smallpawsproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ID;
  private String TypeOfAnimal;
  private int Age;
  private String Description;

  public Animal(String typeOfAnimal, int age, String description)
  {
    TypeOfAnimal = typeOfAnimal;
    Age = age;
    Description = description;
  }

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
