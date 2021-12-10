package com.application.fluxserver.repo;

import com.application.fluxserver.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepo extends ReactiveCrudRepository<UserEntity, Integer> {

    public Flux<UserEntity> findAllById(Integer id);
}
