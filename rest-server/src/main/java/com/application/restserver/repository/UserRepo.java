package com.application.restserver.repository;

import com.application.restserver.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;


@CrossOrigin("http://localhost:8080")
@RepositoryRestResource(collectionResourceRel = "user", path = "users")
public interface UserRepo extends JpaRepository<UserEntity, Long> {

//    UserEntity findByLogin(String login);

    Optional<UserEntity> findByLogin(String login);

    List<UserEntity> findAll();
}
