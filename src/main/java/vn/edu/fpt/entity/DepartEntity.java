package vn.edu.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class DepartEntity implements Serializable {
    @Id
    @Column(name = "id", length = 20)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "departEntity", fetch = FetchType.LAZY)
    private List<StaffEntity> staffEntityList;

    public DepartEntity() {
    }

    public DepartEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaffEntity> getStaffEntityList() {
        return staffEntityList;
    }

    public void setStaffEntityList(List<StaffEntity> staffEntityList) {
        this.staffEntityList = staffEntityList;
    }
}
