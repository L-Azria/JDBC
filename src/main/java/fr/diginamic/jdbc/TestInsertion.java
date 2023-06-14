package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsertion {
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

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connection.createStatement()){
            int nb = st.executeUpdate("insert into FOURNISSEUR (nom) values ('La Maison de la Peinture')");
            System.out.println(connection);
            System.out.println(nb);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }



    }
}
