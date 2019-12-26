package vn.hust.edu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import vn.hust.edu.model.support.ResponseBody;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Certificate {

    @Id
    private String id;

    @Column(name = "code")
    private String code;

    @JsonManagedReference
    @OneToMany(mappedBy = "certificate")
    private Collection<UsageHistory> usageHistories ;

    public Collection<UsageHistory> getUsageHistories() {
        return usageHistories;
    }

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

    /**
     *
     * @param embarkation embarkation station that passenger checkin
     * @param line line that passenger check in
     * @return status of check in
     */
    public abstract ResponseBody checkInResponse(Station embarkation, Line line);


    /**
     *
     * @param disembarkation embarkation station that passenger checkout
     * @param line line that passenger check in
     * @return status of check out
     */
    public abstract ResponseBody checkOutResponse(Station disembarkation, Line line);
}
