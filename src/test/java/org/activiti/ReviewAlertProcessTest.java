package org.activiti;

import org.activiti.builder.AlertDataBuilder;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.model.AlertData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.activiti.constants.AlertProcessConstants.REVIEW_ALERT_PROCESS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartMyReviewAlertProcessApp.class})
@WebAppConfiguration

public class ReviewAlertProcessTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    @Test
    public void testHappyPath() {

        // Create test AlertData
        AlertData alertData = new AlertDataBuilder()
                .with(alertDataBuilder -> {
                    alertDataBuilder.id = 1L;
                    alertDataBuilder.description = "fault_1";
                    alertDataBuilder.status = "OPEN";
                })
                .createAlertData();

        // Start process instance
        Map<String, Object> variables = new HashMap<>();
        variables.put("alertData", alertData);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(REVIEW_ALERT_PROCESS, variables);

        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Assert.assertEquals("User decision (Approve / Reject)", task.getName());

        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .orderByTaskName().asc()
                .list();

        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("User decision (Approve / Reject)", tasks.get(0).getName());

    }

}
