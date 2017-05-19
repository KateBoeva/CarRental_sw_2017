package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;

/**
 * Created by katemrrr on 12.05.17.
 */

@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends CrudRepository<User, Long> {

//    boolean addUser(User user);

//    Token auth(User user);

    //как в классе
//
//    @Override
//    User save(User s);
}
