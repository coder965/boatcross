package com.kelystor.boatcross.entity;

public class JenkinsConfig {
    private String url;
    private String username;
    private String apiToken;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public String toString() {
        return "JenkinsConfig{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", apiToken='" + apiToken + '\'' +
                '}';
    }
}
