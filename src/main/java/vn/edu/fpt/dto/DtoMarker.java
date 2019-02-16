package vn.edu.fpt.dto;

import java.io.Serializable;

public interface DtoMarker<ID extends Serializable> {
    ID getId();
}
