package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;

/**
 * Created by katemrrr on 17.05.17.
 */
public interface SignUpService {

    boolean addUser(User user);

    Token auth(User user);

}
