package vn.edu.fpt.command;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.dto.StaffDto;

import java.util.List;

public class StaffCommand extends AbstractCommand<StaffDto> {

    private MultipartFile staffPhoto;
    private String departId;
    private List<DepartDto> departDtoList;

    public StaffCommand() {
        this.pojo = new StaffDto();
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public List<DepartDto> getDepartDtoList() {
        return departDtoList;
    }

    public void setDepartDtoList(List<DepartDto> departDtoList) {
        this.departDtoList = departDtoList;
    }

    public MultipartFile getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(MultipartFile staffPhoto) {
        this.staffPhoto = staffPhoto;
    }
}
