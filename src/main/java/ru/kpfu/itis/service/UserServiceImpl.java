package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.repository.UserRepository;

/**
 * Created by katemrrr on 12.05.17.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public Token auth(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
