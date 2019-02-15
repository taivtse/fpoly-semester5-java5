package vn.edu.fpt.dto;

import vn.edu.fpt.dto.generic.ActiveGenericDto;

import java.io.Serializable;

public class DepartDto implements Serializable, ActiveGenericDto {
    private String id;
    private String name;
    private Boolean isActive;

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

    @Override
    public Boolean getActive() {
        return isActive;
    }

    @Override
    public void setActive(Boolean active) {
        isActive = active;
    }
}
