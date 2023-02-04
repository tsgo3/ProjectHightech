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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Catégorie getCat() {
        return cat;
    }

    public void setCat(Catégorie cat) {
        this.cat = cat;
    }


}
