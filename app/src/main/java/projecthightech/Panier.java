package projecthightech;

import java.util.List;

public class Panier {
    private double prixpanier;
    private List<Article> listProduits;

    public Panier(double prixpanier, List<Article> listProduits) {
        this.prixpanier = prixpanier;
        this.listProduits = listProduits;
    }

    public double getPrixpanier() {
        return prixpanier;
    }

    public void setPrixpanier(double prixpanier) {
        this.prixpanier = prixpanier;
    }

    public List<Article> getListProduits() {
        return listProduits;
    }

    public void setListProduits(List<Article> listProduits) {
        this.listProduits = listProduits;
    }
}
