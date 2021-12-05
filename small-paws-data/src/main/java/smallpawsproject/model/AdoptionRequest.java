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
//uniqueConstraints = {@UniqueConstraint(columnNames = {"animalId", "userId"})}
public class AdoptionRequest implements Serializable {

    private static final long serialVersionUID = 4521576984820142625L;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;
    private Date date;

    @ManyToOne(targetEntity = Animal.class)
    @JoinColumn(name = "animalId")
    private Animal animalId;

    @ManyToOne(targetEntity = PetOwner.class)
    @JoinColumn(name = "userId", nullable = false)
    private PetOwner userId;

    @ManyToOne(targetEntity = Veterinarian.class, optional = true)
    @JoinColumn( columnDefinition="integer", name = "veterinarianId", nullable = true)
    private Veterinarian veterinarianId;

//    @Column(nullable = true)
    private boolean approve;
    private String animalName;




    public AdoptionRequest( Date date, Animal animalId, PetOwner userId, Veterinarian veterinarianId, boolean approve, String animalName) {
        this.date = date;
        this.animalId = animalId;
        this.userId = userId;
        this.veterinarianId = veterinarianId;
        this.approve = false;
        this.animalName = animalName;
    }

    public void set(boolean approve, Veterinarian veterinarianId, Date date){
        this.approve = approve;
        this.veterinarianId = veterinarianId;
        this.date = date;
    }


    public AdoptionRequest(){}


    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

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
