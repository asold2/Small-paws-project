package smallpawsproject.smallpawsproject.model;

public class Animal
{
  private int ID;
  private String TypeOfAnimal;
  private int Age;
  private String Description;

  public Animal(int ID, String typeOfAnimal, int age, String description)
  {
    this.ID = ID;
    TypeOfAnimal = typeOfAnimal;
    Age = age;
    Description = description;
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
