package ru.kpfu.itis.utils;


/**
 * Created by katemrrr on 17.05.17.
 */
public class Constants {

    public static final String SQL_GET_USER =
            "SELECT * FROM \"users\"";

    public static final String SQL_GET_USER_BY_ID =
            "SELECT * FROM \"users\" where id = :id";

    public static final String SQL_GET_ORDER =
            "SELECT * FROM \"orders\"";

    public static final String SQL_GET_ORDER_BY_ID =
            "SELECT * FROM \"orders\" where id = :id";

    public static final String SQL_GET_CAR =
            "SELECT * FROM \"cars\"";

    public static final String SQL_GET_CAR_BY_ID =
            "SELECT * FROM \"cars\" where id = :id";

    public static final String SQL_EXIST_LOGIN_BY_LOGIN_PASSWORD =
            "SELECT COUNT (*) FROM \"users\" WHERE (login = :login AND password = :password)";

    public static final String SQL_GET_STATUS_BY_LOGIN_PASSWORD =
            "SELECT is_admin FROM \"users\" WHERE (login = :login AND password = :password)";

    public static final String SQL_ADD_USER =
            "INSERT INTO \"users\"(login, password) " +
                    "VALUES (:login, :password)";

    public static final String SQL_ADD_ORDER =
            "INSERT INTO \"orders\"(name, surname, patronymic, model, phone, getting, refunding) " +
                    "VALUES (:name, :surname, :patronymic, :model, :phone, :getting, :refunding)";

    public static final String SQL_ADD_CAR =
            "INSERT INTO \"cars\"(name, price, run, power, year) " +
                    "VALUES (:name, :price, :run, :power, :year)";

    public static final String SQL_UPDATE_ORDER =
            "UPDATE \"orders\" SET name = :name, surname = :surname, patronymic = :patronymic, " +
                    "model = :model, phone = :phone, getting = :getting, refunding = :refunding";

    public static final String SQL_UPDATE_CAR =
            "UPDATE \"cars\" SET name = :name, price = :price, run = :run, power = :power, year = :year";

    public static final String SQL_DELETE_CAR =
            "DELETE FROM \"cars\" WHERE id = :id";

    public static final String SQL_DELETE_ORDER =
            "DELETE FROM \"orders\" WHERE id = :id";
}
