package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by katemrrr on 12.05.17.
 */

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void generateTestData() {
        register(new User("adm", "adm", true));
        register(new User("us", "us", false));
    }

    @Override
    public boolean register(User user) {
        return userRepository.save(user) != null;
    }

    @Override
    public Token auth(User user) {
        Iterable<User> users = userRepository.findAll();
        for (User user1 : users) {
            if(user.getLogin().equals(user1.getLogin()) && user.getPassword().equals(user1.getPassword())){
                if(user1.isAdmin()){
                    return new Token(1, 1);
                } else {
                    return new Token(0, 0);
                }
            }
        }
        return new Token(-1, -1);
    }
}
