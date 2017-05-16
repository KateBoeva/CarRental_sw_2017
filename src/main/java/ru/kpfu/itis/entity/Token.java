package ru.kpfu.itis.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by katemrrr on 12.05.17.
 */

@Entity
public class Token implements Serializable {

    @Id
    private int token;
    private int status;

    public Token() {}

    public Token(int token, int status){
        this.token = token;
        this.status = status;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
