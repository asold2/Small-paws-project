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


    private Animal animalId;


    private PetOwner userId;


    private Veterinarian veterinarianId;


    private boolean approve;
    private String animalName;

    @JsonCreator
    public AdoptionRequest(@JsonProperty("date") Date date,@JsonProperty("animalId") Animal animalId,@JsonProperty("userId") PetOwner userId,@JsonProperty("veterinarianId") Veterinarian veterinarianId,@JsonProperty("approve") boolean approve, @JsonProperty("animalName") String animalName) {

        this.date = date;
        this.animalId = animalId;
        this.userId = userId;
        this.veterinarianId = veterinarianId;
        this.approve = approve;
        this.animalName = animalName;
    }
    public AdoptionRequest(){}

    public void set(boolean approve, Veterinarian veterinarianId, Date date){
        this.approve = approve;
        this.veterinarianId = veterinarianId;
        this.date = date;
    }


    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
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

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Animal getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Animal animalId) {
        this.animalId = animalId;
    }

    public PetOwner getUserId() {
        return userId;
    }

    public void setUserId(PetOwner userId) {
        this.userId = userId;
    }

    public Veterinarian getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Veterinarian veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
