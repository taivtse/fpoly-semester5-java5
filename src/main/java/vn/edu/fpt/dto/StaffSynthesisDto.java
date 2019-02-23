package vn.edu.fpt.dto;

public class StaffSynthesisDto {
    private String staffCode;
    private String staffName;
    private String staffPhoto;
    private Long totalReward;
    private Long totalPunishment;
    private Long totalResult;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Long getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(Long totalReward) {
        this.totalReward = totalReward;
    }

    public Long getTotalPunishment() {
        return totalPunishment;
    }

    public void setTotalPunishment(Long totalPunishment) {
        this.totalPunishment = totalPunishment;
    }

    public Long getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Long totalResult) {
        this.totalResult = totalResult;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(String staffPhoto) {
        this.staffPhoto = staffPhoto;
    }
}
