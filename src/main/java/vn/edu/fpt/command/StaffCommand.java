package vn.edu.fpt.command;

import vn.edu.fpt.dto.DepartDto;
import vn.edu.fpt.dto.StaffDto;

import javax.servlet.http.Part;
import java.util.List;

public class StaffCommand extends AbstractCommand<StaffDto> {

    private Part photo;
    private String departId;
    private List<DepartDto> departDtoList;

    public StaffCommand() {
        this.pojo = new StaffDto();
    }

    public Part getPhoto() {
        return photo;
    }

    public void setPhoto(Part photo) {
        this.photo = photo;
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
}
