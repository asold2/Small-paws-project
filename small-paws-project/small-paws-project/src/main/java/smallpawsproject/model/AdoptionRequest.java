package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class AdoptionRequest implements Serializable {

    private static final long serialVersionUID = 4521576984820142625L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;
    private Date date;


    private Integer animalId;


    private Integer userId;


    private Integer veterinarianId;


    private boolean approve;

    @JsonCreator
    public AdoptionRequest(@JsonProperty("date") Date date,@JsonProperty("animalId") Integer animalId,@JsonProperty("userId") Integer userId,@JsonProperty("veterinarianId") Integer veterinarianId,@JsonProperty("approved") boolean approve) {

        this.date = date;
        this.animalId = animalId;
        this.userId = userId;
        this.veterinarianId = veterinarianId;
        this.approve = approve;
    }
    public AdoptionRequest(){}

    public Integer getRequestid() {
        return requestId;
    }

    public void setRequestid(Integer requestid) {
        this.requestId = requestid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Integer veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
