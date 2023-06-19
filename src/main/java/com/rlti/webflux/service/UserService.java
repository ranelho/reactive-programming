package com.rlti.webflux.service;

import com.rlti.webflux.model.request.UserRequest;
import com.rlti.webflux.entity.User;
import com.rlti.webflux.mapper.UserMapper;
import com.rlti.webflux.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request) {
        return repository.save(mapper.toEntity(request));
    }
}