package ru.dstu;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.dstu.entity.UserFromRest;

@Component
public class UserSaver implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String uri = "http://localhost:8082/users/save";
        RestTemplate restTemplate = new RestTemplate();
        String login = (String) delegateExecution.getVariable("login");
        String password = (String) delegateExecution.getVariable("password");
        UserFromRest user = restTemplate.postForObject(uri,new UserFromRest(null, login,password), UserFromRest.class);
    }
}
