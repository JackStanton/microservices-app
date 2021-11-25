package com.application.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.dstu.generated.User;
import ru.dstu.generated.UserRequest;
import ru.dstu.generated.UserResponse;


public class RequestExecutor extends WebServiceGatewaySupport {

    public UserResponse getUser(User user) {
        UserRequest request = new UserRequest();
        request.setUser(user);
        return (UserResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws", request);
    }
}
