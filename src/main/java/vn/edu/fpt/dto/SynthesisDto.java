package vn.edu.fpt.dto;

public class SynthesisDto {
    private String staffCode;
    private Integer totalReward;
    private Integer totalPunishment;
    private Integer totalResult;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Integer getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(Integer totalReward) {
        this.totalReward = totalReward;
    }

    public Integer getTotalPunishment() {
        return totalPunishment;
    }

    public void setTotalPunishment(Integer totalPunishment) {
        this.totalPunishment = totalPunishment;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }
}
