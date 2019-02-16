package vn.edu.fpt.dto.generic;

import vn.edu.fpt.dto.DtoMarker;

import java.io.Serializable;

public interface ActiveGenericDto<ID extends Serializable> extends DtoMarker<ID> {
    Boolean getActive();

    void setActive(Boolean active);
}
