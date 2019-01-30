package vn.edu.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "record")
public class RecordEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false, length = 10)
    private String type;

    @Column(name = "reason", nullable = false, length = 510)
    private String reason;

    @Column(name = "submit_date", nullable = false)
    private Date submitDate;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private StaffEntity staffEntity;

    public RecordEntity() {
    }

    public RecordEntity(String type, String reason, Date submitDate, StaffEntity staffEntity) {
        this.type = type;
        this.reason = reason;
        this.submitDate = submitDate;
        this.staffEntity = staffEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public StaffEntity getStaffEntity() {
        return staffEntity;
    }

    public void setStaffEntity(StaffEntity staffEntity) {
        this.staffEntity = staffEntity;
    }
}
