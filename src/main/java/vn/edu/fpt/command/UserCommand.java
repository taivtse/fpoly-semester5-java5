package vn.edu.fpt.command;

import vn.edu.fpt.dto.UserDto;

public class UserCommand extends AbstractCommand<UserDto> {
    public UserCommand() {
        this.pojo = new UserDto();
    }
}
