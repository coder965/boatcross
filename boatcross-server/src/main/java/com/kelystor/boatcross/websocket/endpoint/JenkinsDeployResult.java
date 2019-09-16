package com.kelystor.boatcross.websocket.endpoint;

import java.io.Serializable;

public class JenkinsDeployResult implements Serializable {
    private String phase;
    private String message;
    private String project;

    public static JenkinsDeployResult build(String project, String message) {
        JenkinsDeployResult result = new JenkinsDeployResult();
        result.setMessage(message);
        result.setPhase("build");
        result.setProject(project);
        return result;
    }

    public static JenkinsDeployResult complete() {
        JenkinsDeployResult result = new JenkinsDeployResult();
        result.setPhase("complete");
        return result;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
