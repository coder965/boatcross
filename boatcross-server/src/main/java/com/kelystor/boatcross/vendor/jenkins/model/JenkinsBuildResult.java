package com.kelystor.boatcross.vendor.jenkins.model;

public class JenkinsBuildResult {
    private Integer number;
    private Boolean building;
    private String result;
    private Long timestamp;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getBuilding() {
        return building;
    }

    public void setBuilding(Boolean building) {
        this.building = building;
    }

    public boolean isSuccess() {
        return "SUCCESS".equals(result);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "JenkinsBuildResult{" +
                "number=" + number +
                ", building=" + building +
                ", result='" + result + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
