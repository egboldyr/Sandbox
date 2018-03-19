package soap.entity;

import soap.entity.enums.RequisitionStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Entity
@Table(name = "REQUISITIONS")
@XmlRootElement(name = "requisition")
@XmlType(propOrder = {"id", "name", "phoneNumber", "email", "comment", "status", "creationDate"})
public class Requisition {

    @Id
    @SequenceGenerator(name = "requisitionId", sequenceName = "seq_requisition_id", initialValue = 5000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisitionId")
    private Long id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String comment;

    @Enumerated(EnumType.STRING)
    private RequisitionStatus status;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public Requisition() {}

    public Requisition(String name, String phoneNumber, String email, String comment) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.comment = comment;
        status = RequisitionStatus.NEW;
        creationDate = Calendar.getInstance().getTime();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public RequisitionStatus getStatus() {
        return status;
    }
    public void setStatus(RequisitionStatus status) {
        this.status = status;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
