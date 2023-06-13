package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entities.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestSelect {
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
            ResultSet curseur = st.executeQuery("SELECT ID, NOM FROM FOURNISSEUR");
            ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

            while (curseur.next()){
                Integer id = curseur.getInt("id");
                String nom = curseur.getString("NOM");

                Fournisseur fournisseurCourant = new Fournisseur(id, nom);
                fournisseurs.add(fournisseurCourant);
            }

            System.out.println(fournisseurs);



        } catch (SQLException e){
            System.err.println(e.getMessage());
        }



    }

}
