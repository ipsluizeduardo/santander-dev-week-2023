package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRespository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;

    public UserServiceImpl(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public User findById(Long id) {
        return userRespository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRespository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw  new IllegalArgumentException("This Account number already exists");
        }
        return userRespository.save(userToCreate);
    }
}
