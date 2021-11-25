package com.soapserver.endpoint;

import com.soapserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.dstu.generated.UserRequest;
import ru.dstu.generated.UserResponse;

@Endpoint
public class UserEndpoint {

    private final String NAMESPACE_URI = "generated.dstu.ru";

    @Autowired
    UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "userRequest")
    @ResponsePayload
    public UserResponse getUsers(@RequestPayload UserRequest request) {
        UserResponse response = new UserResponse();
        String login = request.getUser().getLogin();
        if (login.equals("")){
            response.getUsers().addAll(userService.findUsers());
        }else {
            response.getUsers().addAll(userService.findUser(request.getUser()));
        }
        return response;
    }
}
