package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entities.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc implements IFournisseurDao {

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

    @Override
    public List<Fournisseur> extraire() {
        List<Fournisseur> resultat = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement st = connection.createStatement();
            ResultSet curseur = st.executeQuery("SELECT ID, NOM FROM FOURNISSEUR")){

            while (curseur.next()){
                Integer id = curseur.getInt("id");
                String nom = curseur.getString("NOM");

                Fournisseur fournisseurCourant = new Fournisseur(id, nom);
                resultat.add(fournisseurCourant);
            }


        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return resultat;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement st = connection.createStatement()){
            //System.out.println(fournisseur.getNom());
            int nb = st.executeUpdate("insert into FOURNISSEUR (NOM) values ('" + fournisseur.getNom()+ "')");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int nb = 0;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement st = connection.createStatement()){
            nb = st.executeUpdate("UPDATE FOURNISSEUR SET NOM = '" + nouveauNom +"' WHERE NOM = '" + ancienNom+ "'");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return nb;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        int nb =0;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement st = connection.createStatement()){
            nb = st.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM = '" +fournisseur.getNom()+"'");
            System.out.println(connection);
            System.out.println(nb);

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return nb > 0;
    }
}
