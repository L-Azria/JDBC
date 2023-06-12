package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {
    private static final String URL = "jdbc:mariadb://localhost:3306/COMPTA2" ;
    private static final String USER = "root";
    private static final String PASSWORD = "Linhl@n02052015";
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            System.out.println(connection);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }
}
