package vn.hust.edu.model;

import lombok.Data;
import vn.hust.edu.model.support.ResponseBody;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Certificate {

    @Id
    private String id;

    @Column(name = "code")
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseBody checkInResponse(Station embarkation, UsingHistory history){
        return null;
    }

    public ResponseBody checkOutResponse(Station disembarkation, UsingHistory history){
        return null;
    }
}
