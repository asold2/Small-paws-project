package smallpawsproject.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "certificates")

public class Certificate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "requestId", referencedColumnName = "requestId")
    private AdoptionRequest requestId;
    @ManyToOne(targetEntity = PetOwner.class)
    @JoinColumn(name = "petOwnerId", nullable = false)
    private PetOwner petOwnerId;

    @Lob
    @Nullable
    private byte[] document;

    public Certificate(){}

    public Certificate(AdoptionRequest adoptionRequest, byte[] document){
        this.requestId = adoptionRequest;
        this.document = document;
        this.petOwnerId = adoptionRequest.getUserId();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public AdoptionRequest getAdoptionRequestFrom() {
        return requestId;
    }

    public void setAdoptionRequestFrom(AdoptionRequest adoptionRequestFrom) {
        this.requestId = adoptionRequestFrom;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public PetOwner getPetOwnerId() {
        return petOwnerId;
    }

    public void setPetOwnerId(PetOwner petOwnerId) {
        this.petOwnerId = petOwnerId;
    }
}
