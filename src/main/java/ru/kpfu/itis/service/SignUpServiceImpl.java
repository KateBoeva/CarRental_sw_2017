package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Token;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.utils.Constants;
import ru.kpfu.itis.utils.ParamsMapper;
import ru.kpfu.itis.utils.SqlQueryExecutor;

import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by katemrrr on 17.05.17.
 */
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    Verifier verifier;

    @Autowired
    private SqlQueryExecutor sqlQueryExecutor;

    @Autowired
    private ParamsMapper paramsMapper;

    @Override
    public boolean addUser(User user) {
        try {
            verifier.verifyLogin(user.getLogin(), user.getPassword());
            return false;
        } catch (IllegalArgumentException e) {
            Map<String, Object> paramMap = paramsMapper.asMap(asList("login", "password"),
                    asList(user.getLogin(), user.getPassword()));
            sqlQueryExecutor.updateQuery(Constants.SQL_ADD_USER, paramMap);
            return true;
        }
    }

    @Override
    public Token auth(User user) {
//        try {
//            verifier.verifyLogin(user.getLogin(), user.getPassword());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Login/password incorrect");
//        }
//        Map<String, Object> paramMap = paramsMapper.asMap(asList("login", "password"),
//                asList(user.getLogin(), user.getPassword()));
//        int status = sqlQueryExecutor.queryForInt(Constants.SQL_GET_STATUS_BY_LOGIN_PASSWORD, paramMap);
//        return new Token(status==1?"6c7ca345f63f835cb353ff15bd6c5e052ec08e7a":"9c031d62a3c4909b216e1d86b7f69b982bdca0f9", status);
        return null;
    }
}
