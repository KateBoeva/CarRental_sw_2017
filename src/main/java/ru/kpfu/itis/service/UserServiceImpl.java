package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;

/**
 * Created by katemrrr on 12.05.17.
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public Token auth(User user) {
        return null;
    }
}
