package vn.edu.fpt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
public class StaffEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "photo", length = 100)
    private String photo;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depart_id", nullable = false)
    private DepartEntity departEntity;

    @OneToMany(mappedBy = "staffEntity", fetch = FetchType.LAZY)
    private List<RecordEntity> recordEntityList;

    public StaffEntity() {
    }

    public StaffEntity(String name, String gender, Date birthday, String photo, String email, String phone, Integer salary, String notes, DepartEntity departEntity) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.notes = notes;
        this.departEntity = departEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public DepartEntity getDepartEntity() {
        return departEntity;
    }

    public void setDepartEntity(DepartEntity departEntity) {
        this.departEntity = departEntity;
    }

    public List<RecordEntity> getRecordEntityList() {
        return recordEntityList;
    }

    public void setRecordEntityList(List<RecordEntity> recordEntityList) {
        this.recordEntityList = recordEntityList;
    }
}
