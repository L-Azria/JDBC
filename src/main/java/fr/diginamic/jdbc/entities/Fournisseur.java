package fr.diginamic.jdbc.entities;

public class Fournisseur {
    private Integer id;
    private String nom;

    public Fournisseur(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Fournisseur : " + "id=" + id + " , nom= " + nom + " \n";
    }
}
