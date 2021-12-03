package smallpawsproject.model;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

@Entity
@Transactional
@DynamicUpdate
@Table (name= "requests")
public class AdoptionRequest implements Serializable {

    private static final long serialVersionUID = 4521576984820142625L;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;
    private Date date;

    @ManyToOne(targetEntity = Animal.class)
    @JoinColumn(name = "animalId")
    private Integer animalId;

    @ManyToOne(targetEntity = PetOwner.class)
    @JoinColumn(name = "userId", nullable = false)
    private Integer userId;

    @ManyToOne(targetEntity = Veterinarian.class)
    @JoinColumn(name = "veterinarianId", nullable = false)
    private Integer veterinarianId;

    private boolean approve;


    public AdoptionRequest( Date date, Integer animalId, Integer userId, Integer veterinarianId, boolean approve) {
        this.date = date;
        this.animalId = animalId;
        this.userId = userId;
        this.veterinarianId = veterinarianId;
        this.approve = false;
    }
    public AdoptionRequest(){}

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
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
