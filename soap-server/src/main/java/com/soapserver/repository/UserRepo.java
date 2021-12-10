package com.soapserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.dstu.generated.User;

import java.util.List;

@CrossOrigin("http://localhost:8081")
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByLogin(String login);
}
