package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;

/**
 * Created by katemrrr on 12.05.17.
 */
public interface UserService {

    boolean addUser(User user);

    Token auth(User user);

    User save(User user);

}
