package com.soapserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dstu.generated.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByLogin(String login);
}
