package org.activiti.service;

import org.activiti.engine.RuntimeService;
import org.activiti.model.AlertData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.activiti.constants.AlertProcessConstants.*;

@Component
@Transactional
public class ReviewAlertProcessService {

    @Autowired
    private RuntimeService runtimeService;

    private final RestTemplate restTemplate;

    public ReviewAlertProcessService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void createAlertDemoUsers() {
        this.restTemplate.postForEntity(SAVE_ALERT_DATA_URL, HttpRequest.class, HttpStatus.class);
    }

    public void startProcessWithAlertData(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_ALERT_DATA_BY_ID_URL).queryParam(ID, id);
        ResponseEntity<AlertData> resp = this.restTemplate.getForEntity(builder.toUriString(), AlertData.class);
        Map<String, Object> variables = new HashMap<>();
        variables.put("alertData", resp.getBody());
        runtimeService.startProcessInstanceByKey(REVIEW_ALERT_PROCESS, variables);
    }

    public void startProcessWithAllAlertData() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GET_ALL_ALERT_DATA_URL);
        ResponseEntity<List> resp = this.restTemplate.getForEntity(builder.toUriString(), List.class);

        Map<String, Object> variables = new HashMap<>();
        variables.put("alertDataList", resp.getBody());
        runtimeService.startProcessInstanceByKey(REVIEW_ALERT_PROCESS, variables);
    }

    public void updateAlertTask(Long id, String status) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(UPDATE_ALERT_DATA_BY_ID_URL).queryParam(ID, id).queryParam(STATUS, status);
        this.restTemplate.postForEntity(builder.toUriString(), HttpRequest.class, HttpStatus.class);
    }

}
