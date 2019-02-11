package vn.edu.fpt.service;

import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.service.generic.GenericService;

import java.util.List;

public interface DepartService extends GenericService<String, DepartDto> {
    List<DepartDto> findAllActive();
}
