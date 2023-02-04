package projecthightech;

public class Article {
    private int id;
    private String libelle;
    private String marque;
    private double prix;
    private String photo;
    private Catégorie cat;

    public Article(int id, String libelle, String marque, double prix, String photo, Catégorie cat) {
        this.id = id;
        this.libelle = libelle;
        this.marque = marque;
        this.prix = prix;
        this.photo = photo;
        this.cat = cat;
    }
}
