package fr.diginamic.jdbc.dao;

import fr.diginamic.jdbc.entities.Fournisseur;

import java.util.List;

public interface IFournisseurDao {
    List<Fournisseur> extraire();
    void insert(Fournisseur fournisseur);
    int update(String ancienNom , String nouveauNom);
    boolean delete(Fournisseur fournisseur);
}
