package vn.edu.fpt.dto;

public class DepartSynthesisDto {
    private String departId;
    private String departName;
    private Long totalReward;
    private Long totalPunishment;
    private Long totalResult;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
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
}
