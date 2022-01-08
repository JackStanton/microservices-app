package com.soapserver.service;

import com.soapserver.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dstu.generated.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> findUser(User user){
        List<User> users = userRepo.findAllByLogin(user.getLogin());
//        if(users.size() == 0){
//            saveUser(user);
//        }
        return users;
    }

    public Optional<User> findUserById(Long id){
        return userRepo.findById(id);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> findUsers() {
        return userRepo.findAll();
    }
}
