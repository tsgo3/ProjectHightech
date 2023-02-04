package projecthightech;

public class Commande {

    private int quantite;
    private double prixTotal;

    public Commande(int quantite, double prixTotal) {
        this.quantite = quantite;
        this.prixTotal = prixTotal;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
