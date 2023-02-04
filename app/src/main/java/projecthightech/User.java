package projecthightech;

public class User {
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
}
