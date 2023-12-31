package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionJdbc {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
        String driver = monFichierConf.getString("database.driver");
        URL = monFichierConf.getString("database.url");
        USER = monFichierConf.getString("database.user");
        PASSWORD = monFichierConf.getString("database.password");

    }

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            System.out.println(connection);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
