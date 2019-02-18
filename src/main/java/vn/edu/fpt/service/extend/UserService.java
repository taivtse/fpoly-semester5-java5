package vn.edu.fpt.service.extend;

import vn.edu.fpt.dto.UserDto;
import vn.edu.fpt.service.generic.GenericService;

import java.util.List;

public interface UserService extends GenericService<Integer, UserDto> {
    List<UserDto> findAllByFullName(String name);
    UserDto findOneByUsername(String username);
}
