package com.application.restserver.services;

import com.application.restserver.entity.UserEntity;
import com.application.restserver.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public Iterable<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }

    public UserEntity saveOrUpdate(UserEntity entity){
        userRepository.save(entity);
        return userRepository.findByLogin(entity.getLogin()).get();
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

}
