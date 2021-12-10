package com.application.soapclient.controller;

import com.application.soapclient.RequestExecutor;
import com.application.soapclient.entity.UserFromRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.dstu.generated.User;
import ru.dstu.generated.UserResponse;

import java.util.*;

@Controller
@RequestMapping("/restUsers")
public class RestDataGetter {

    @Autowired
    RequestExecutor requestExecutor;

    @GetMapping({"/get/{id}"})
    public String getUser(@PathVariable Long id, Model model){
        String uri = "http://localhost:8082/users/"+id;
        RestTemplate restTemplate = new RestTemplate();
        Optional<LinkedHashMap> users = (Optional<LinkedHashMap>) restTemplate.getForObject(uri, Optional.class);
        model.addAttribute("users",getUser(users));
        model.addAttribute("user",new UserFromRest());
        return "views/users";
    }

    @GetMapping({"/list"})
    public String getUsers(Model model){
        String uri = "http://localhost:8082/users/list";
        RestTemplate restTemplate = new RestTemplate();
        Iterable users = restTemplate.getForObject(uri, Iterable.class);
        model.addAttribute("users",getUsers(users));
        model.addAttribute("user",new UserFromRest());
        return "views/users";
    }

    @PostMapping({"/save"})
    public String saveUser(Model model, @ModelAttribute("user") UserFromRest userFromRest){
        User userSoap = new User(userFromRest.getId(),userFromRest.getLogin(),userFromRest.getPassword());
        UserResponse response = requestExecutor.getUser(userSoap);
        List<User> users = response.getUsers();
        if(users.size() == 0){
            String uri = "http://localhost:8082/users/save";
            RestTemplate restTemplate = new RestTemplate();
            UserFromRest user = restTemplate.postForObject(uri,userFromRest, UserFromRest.class);
            assert user != null;
            return "redirect:/restUsers/get/"+user.getId();
        }else {
            model.addAttribute("user", userFromRest);
            model.addAttribute("message", "1");
            return "/views/userEdit";
        }
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") UserFromRest userFromRest, Model model) {
        String uri  = "http://localhost:8082/users/"+userFromRest.getId();
        RestTemplate restTemplate = new RestTemplate();
        Optional<LinkedHashMap> users = (Optional<LinkedHashMap>) restTemplate.getForObject(uri, Optional.class);
        UserFromRest user = getUser(users).get(0);
        model.addAttribute("user", user);
        model.addAttribute("title", "Редактировать пользователя:");
        model.addAttribute("action", "/restUser/save");
        model.addAttribute("message", "3");
        return "/views/userEdit";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new UserFromRest());
        model.addAttribute("title", "Добавить пользователя:");
        model.addAttribute("action", "/restUser/save");
        model.addAttribute("message", "3");
        return "views/userEdit";
    }

    @PostMapping("/deleteUser")
    public String deleteStudent(@ModelAttribute("user") UserFromRest userFromRest) {
        String uri  = "http://localhost:8082/users/delete/"+userFromRest.getId();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
        return "redirect:/restUsers/list";
    }

    private ArrayList<UserFromRest> getUser(Optional<LinkedHashMap> users){
        ArrayList<UserFromRest> userList = new ArrayList<>();
        if(users != null){
            users.stream().forEach(o ->{
                UserFromRest user =new UserFromRest(Long.valueOf((Integer)o.get("id")), o.get("login").toString(),o.get("password").toString());
                userList.add(user);
            });
        }
        return userList;
    }

    private ArrayList<UserFromRest> getUsers(Iterable iterable){
        ArrayList<UserFromRest> userList = new ArrayList<>();
        iterable.forEach(o ->{
            Map map = (Map) o;
            UserFromRest user =new UserFromRest(Long.valueOf((Integer)map.get("id")), map.get("login").toString(),map.get("password").toString());
            userList.add(user);
        });
        return userList;
    }
}
