package projecthightech;

public class User{
    private int id;
    private String mail;
    private String nomUtilisateur;
    private String password;

    public User(int id, String mail, String nomUtilisateur, String password)
    {
        this.id=id;
        this.mail=mail;
        this.nomUtilisateur=nomUtilisateur;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
