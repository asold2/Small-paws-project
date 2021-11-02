package smallpawsproject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smallpawsproject.model.Employee;
import smallpawsproject.model.PetOwner;
import smallpawsproject.repositories.EmployeeRepository;
import smallpawsproject.services.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

  private JSONArray jsonArray = new JSONArray();
  private JSONObject jsonObject;
  JSONParser parser = new JSONParser();
  private final FileReader reader = new FileReader("small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/employees.json");
  private final ObjectMapper objectMapper = new ObjectMapper();
  private List<Employee> employees;
  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository)throws FileNotFoundException
  {
    this.employeeRepository = employeeRepository;
    try
    {
      jsonArray = (JSONArray) parser.parse(reader);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    try
    {
      employees = new ArrayList<>();
      employees = objectMapper.readValue(jsonArray.toJSONString(), new TypeReference<List<Employee>>(){});
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }

  }



  public void writeEmployeeToJsonFile(Employee employee)
  {
    jsonObject = new JSONObject();
    jsonObject.put("id",employee.getId());
    jsonObject.put("userName",employee.getUserName());
    jsonObject.put("password",employee.getPassword());
    jsonObject.put("role",employee.getRole());

    jsonArray.add(jsonObject);

    try {
      FileWriter fileWriter = new FileWriter("small-paws-project/small-paws-project/src/main/java/smallpawsproject/jsonFiles/employees.json");
      fileWriter.write(jsonArray.toJSONString());
      fileWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override public int authenticateEmployee(String username, String password)
  {
    for(Employee employee : employees){
      if ((employee.getUserName().equals(username)) && (employee.getPassword()
          .equals(password)))
      {
        return HttpServletResponse.SC_ACCEPTED;
      }
      else
      {
        return HttpServletResponse.SC_FORBIDDEN;
      }
    }


    return HttpServletResponse.SC_BAD_GATEWAY;
  }
}
