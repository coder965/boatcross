package com.kelystor.boatcross.websocket.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JenkinsDeployResultEncoder implements Encoder.Text<JenkinsDeployResult> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(JenkinsDeployResult jenkinsDeployResult) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(jenkinsDeployResult);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {

    }
}
