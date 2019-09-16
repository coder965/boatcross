package com.kelystor.boatcross.vendor.jenkins;

import com.kelystor.boatcross.mapper.json.JackJsonMapper;
import com.kelystor.boatcross.mapper.json.JsonMapper;
import com.kelystor.boatcross.vendor.exception.ApiRequestException;
import com.kelystor.boatcross.vendor.jenkins.model.JenkinsBuildResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JenkinsAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsAPI.class);
    private static final JsonMapper MAPPER = new JackJsonMapper();
    private static final Pattern BUILD_VERSION_PATTERN = Pattern.compile("docker push (?:.+?):(v[\\d]+)$", Pattern.MULTILINE);

    private String hostUrl;
    private String username;
    private String apiToken;

    private JenkinsAPI(String hostUrl, String username, String apiToken) {
        this.hostUrl = hostUrl;
        this.username = username;
        this.apiToken = apiToken;
    }

    public static JenkinsAPI connect(String hostUrl, String username, String apiToken) {
        return new JenkinsAPI(hostUrl, username, apiToken);
    }

    public JenkinsBuildResult getLastBuildResult(String project) {
        final String projectUrlEncoded = sanitizeProjectName(project);
        final String lastBuildResultUrl = hostUrl + "/job/" + projectUrlEncoded + "/lastBuild/api/json";

        String content = executeRequest(new HttpGet(lastBuildResultUrl));
        return MAPPER.toObject(content, JenkinsBuildResult.class);
    }

    public String getLastBuildVersion(String project) {
        final String projectUrlEncoded = sanitizeProjectName(project);
        final String lastBuildResultUrl = hostUrl + "/job/" + projectUrlEncoded + "/lastBuild/consoleText";

        String consoleText = executeRequest(new HttpGet(lastBuildResultUrl));
        return getBuildVersionFromConsole(consoleText);
    }

    public void triggerBuild(String project, String token) {
        final String projectUrlEncoded = sanitizeProjectName(project);
        final String triggerBuildUrl = hostUrl + "/job/" + projectUrlEncoded + "/build?token=" + sanitizeProjectName(token);

        executeRequest(new HttpGet(triggerBuildUrl));
    }

    private String executeRequest(HttpUriRequest request) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            addAuthHeader(request);
            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String content = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                if (!Arrays.asList(HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_ACCEPTED).contains(statusCode)) {
                    LOGGER.error("Jenkins请求失败，请求url：{}，状态码：{}，返回内容：{}", request.getURI(), statusCode, content);
                    throw new ApiRequestException("Jenkins请求失败，状态码：" + statusCode);
                }
                return content;
            }
        } catch (IOException e) {
            throw new ApiRequestException("Jenkins请求失败，未获取服务器响应");
        }
    }

    private String getBuildVersionFromConsole(String consoleText) {
        Matcher matcher = BUILD_VERSION_PATTERN.matcher(consoleText);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String sanitizeProjectName(String projectName) {
        try {
            return URLEncoder.encode(projectName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new ApiRequestException("Jenkins项目名称 " + projectName + " URLEncoder.encode失败");
        }
    }

    private void addAuthHeader(HttpUriRequest request) {
        String auth = username + ":" + apiToken;
        byte[] authBytes = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        request.addHeader("Authorization", "Basic " + new String(authBytes));
    }
}
