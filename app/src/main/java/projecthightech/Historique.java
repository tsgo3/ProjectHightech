package projecthightech;

import java.util.List;

public class Historique {
    private List<Panier> listePanier;

    public Historique(List<Panier> listePanier) {
        this.listePanier = listePanier;
    }

    public List<Panier> getListePanier() {
        return listePanier;
    }

    public void setListePanier(List<Panier> listePanier) {
        this.listePanier = listePanier;
    }


}
