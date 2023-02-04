package projecthightech;

import java.sql.*;

public class ArticleDAO
{
    private static final String URL ="jdbc:mysql://localhost:3306/hightech";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "190201";

    public Article getcaracteristique(int id) throws SQLException
    {
        Article article = null;

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))
        {
            Class.forName("com.mysql.jdbc.Driver");
            String query = "SELECT * FROM articles WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                int articleId = resultSet.getInt("id");
                String libelle = resultSet.getString("libelle");
                String marque = resultSet.getString("marque");
                double prix = resultSet.getDouble("prix");
                String photo = resultSet.getString("photo");
                Categorie categorie = new Categorie(resultSet.getString("nom"));

                article = new Article(articleId, libelle, marque, prix, photo, categorie);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return article;
    }
}
