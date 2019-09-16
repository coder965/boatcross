package com.kelystor.boatcross.websocket.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class JenkinsDeployRequestDecoder implements Decoder.Text<JenkinsDeployRequest> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JenkinsDeployRequest decode(String s) throws DecodeException {
        try {
            return objectMapper.readValue(s, JenkinsDeployRequest.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
