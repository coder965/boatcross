package com.kelystor.boatcross.websocket.endpoint;

import java.util.List;

public class JenkinsDeployRequest {
    private List<String> projects;

    enum UpdateMethod {
        STANDARD,
        BLUE_GREEN,
        INTELLIGENT;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
}
