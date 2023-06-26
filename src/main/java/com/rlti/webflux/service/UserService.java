package com.rlti.webflux.service;

import com.rlti.webflux.model.request.UserRequest;
import com.rlti.webflux.entity.User;
import com.rlti.webflux.mapper.UserMapper;
import com.rlti.webflux.repository.UserRepository;
import com.rlti.webflux.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request) {
        return repository.save(mapper.toEntity(request));
    }

    public Mono<User> findById(final String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ObjectNotFoundException(
                                format("Object not Found. Id: %s, Type: %s ", id, User.class.getSimpleName())
                        )
                ));
    }
}
