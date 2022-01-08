package ru.dstu;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.application.soapclient.RequestExecutor;
import ru.dstu.generated.User;
import ru.dstu.generated.UserResponse;

import java.util.*;

@Component
public class UserChecker implements JavaDelegate {

    @Autowired
    RequestExecutor requestExecutor;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String login = (String) delegateExecution.getVariable("login");
        String password = (String) delegateExecution.getVariable("password");
        User userSoap = new User(null,login,password);
        UserResponse response = requestExecutor.getUser(userSoap);
        List<User> users = response.getUsers();
        delegateExecution.setVariable("validUser", users.isEmpty());
    }
}
