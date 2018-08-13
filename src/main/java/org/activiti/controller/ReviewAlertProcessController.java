package org.activiti.controller;

import org.activiti.model.AlertData;
import org.activiti.service.ReviewAlertProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewAlertProcessController {

    @Autowired
    private ReviewAlertProcessService reviewAlertProcessService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/initiate-alert-process", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void initiateAlertProcess(@RequestBody AlertData alertData) {
        reviewAlertProcessService.startProcessWithAlertData(alertData.getId());
    }

}
