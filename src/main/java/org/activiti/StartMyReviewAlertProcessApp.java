package org.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.service.ReviewAlertProcessService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class StartMyReviewAlertProcessApp {

    public static void main(String[] args) {
        SpringApplication.run(StartMyReviewAlertProcessApp.class, args);
    }

    @Bean
    public CommandLineRunner init(final ReviewAlertProcessService alertStatusService) {
        return new CommandLineRunner() {
            public void run(String... strings) throws Exception {
                alertStatusService.createAlertDemoUsers();
            }
        };

    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

                Group group = identityService.newGroup("user");
                group.setName("users");
                group.setType("security-role");
                identityService.saveGroup(group);

                User admin = identityService.newUser("admin");
                admin.setPassword("admin");
                identityService.saveUser(admin);

            }
        };
    }

}
