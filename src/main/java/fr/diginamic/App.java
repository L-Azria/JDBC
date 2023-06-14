package fr.diginamic;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.dao.IFournisseurDao;
import fr.diginamic.jdbc.entities.Fournisseur;

import java.util.List;

public class App {
    public static void main(String[] args) {
        listeFournisseurs();
        modifierFournisseurs("FDM SA 5", "FDM SA 4");
        insererFournisseurs(new Fournisseur("azerty"));
        supprimerFournisseurs (new Fournisseur("azer"));
    }

    private static void listeFournisseurs() {
        IFournisseurDao dao = new FournisseurDaoJdbc();
        List<Fournisseur> fournisseurs = dao.extraire();
        for (Fournisseur f : fournisseurs) {
            System.out.println(f);
        }
    }

    private static void modifierFournisseurs(String nvNom, String ancienNom) {
        try {
            IFournisseurDao dao = new FournisseurDaoJdbc();
            System.out.println(dao.update(ancienNom, nvNom));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void insererFournisseurs(Fournisseur fournisseur) {
        try {
            IFournisseurDao dao = new FournisseurDaoJdbc();
            dao.insert(fournisseur);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void supprimerFournisseurs(Fournisseur fournisseur) {
        try {
          IFournisseurDao dao = new FournisseurDaoJdbc();
          System.out.println(dao.delete(fournisseur));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



}
