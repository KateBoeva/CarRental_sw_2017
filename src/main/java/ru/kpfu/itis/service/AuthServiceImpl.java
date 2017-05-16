package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.repository.UserRepository;

/**
 * Created by katemrrr on 12.05.17.
 */
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean register(User user) {
        return userRepository.save(user) != null;
    }

    @Override
    public Token auth(User user) {
//        return userRepository.auth(user);
        return new Token(12222, 1);
    }
}