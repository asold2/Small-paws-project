package smallpawsproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Certificate implements Serializable {

    @Id
    private Integer Id;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "requestId", referencedColumnName = "requestId")
    private AdoptionRequest requestId;
    @Lob
    @Nullable
    private byte[] document;
    private PetOwner petOwnerId;

    public Certificate(@JsonProperty("requestId") AdoptionRequest adoptionRequestFrom,@JsonProperty("document") byte[] document) {
        this.requestId = adoptionRequestFrom;
        this.document = document;
        this.petOwnerId = adoptionRequestFrom.getUserId();
    }
    public Certificate(){}

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
