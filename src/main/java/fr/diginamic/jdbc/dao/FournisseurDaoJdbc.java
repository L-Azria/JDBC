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
            PreparedStatement st = connection.prepareStatement("SELECT * FROM FOURNISSEUR");
            ResultSet curseur = st.executeQuery()){

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
            PreparedStatement pst = connection.prepareStatement("insert into FOURNISSEUR(NOM) values(?)")) {
            //System.out.println(fournisseur.getNom());
            pst.setString(1, fournisseur.getNom());
            pst.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        int nb = 0;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM = ? WHERE NOM = ?")){
            pst.setString(1, nouveauNom);
            pst.setString(2, ancienNom);
            nb = pst.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return nb;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        int nb =0;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE NOM = ?")){
            pst.setString(1, fournisseur.getNom());
            nb = pst.executeUpdate();

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return nb > 0;
    }
}
