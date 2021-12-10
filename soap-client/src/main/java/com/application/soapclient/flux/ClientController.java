package com.application.soapclient.flux;

import com.application.soapclient.entity.UserFromRest;
import com.application.soapclient.flux.entity.UserEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Controller
@RequestMapping("/")
public class ClientController {

    @RequestMapping("/getUserList")
    public String test(Model model){
        Flux<UserEntity> usersFlux = getHeadersSpec("/list").retrieve().bodyToFlux(UserEntity.class);
        model.addAttribute("users", usersFlux.toIterable());
        model.addAttribute("user",new UserFromRest());
        return "views/usersFlux";
    }

    private WebClient.RequestHeadersSpec<?> getHeadersSpec(String action){
        return getWebClient().get()
                .uri(action);
    }

    private WebClient.RequestHeadersSpec<?> postHeadersSpec(String action, UserEntity bodyValue){
        return getWebClient().post()
                .uri(action).bodyValue(bodyValue);
    }

    private WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8086")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
