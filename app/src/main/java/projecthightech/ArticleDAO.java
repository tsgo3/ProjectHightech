package projecthightech;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Path("/articles")
public class ArticleDAO
{
    private static final String URL ="jdbc:mysql://localhost:3306/hightech";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "190201";

    private int i = 0;
    Map<Integer, Article> contentProvider = new HashMap<>();
    public Map<Integer, Article> getallcaracteristique()
    {

        Article article;
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))
        {
            String query = "SELECT * FROM article";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                int articleId = resultSet.getInt("idarticle");
                String libelle = resultSet.getString("libelle");
                String marque = resultSet.getString("marque");
                double prix = resultSet.getDouble("prix");
                String photo = resultSet.getString("photo");
                Categorie categorie = new Categorie(resultSet.getString("categorie"));

                article = new Article(articleId, libelle, marque, prix, photo, categorie);
                contentProvider.put(i, article);
                i++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return contentProvider;
    }

    public Article getcaracteristique(int id) throws SQLException
    {
        Article article = null;

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD))
        {
            String query = "SELECT * FROM article WHERE idarticle = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                int articleId = resultSet.getInt("idarticle");
                String libelle = resultSet.getString("libelle");
                String marque = resultSet.getString("marque");
                double prix = resultSet.getDouble("prix");
                String photo = resultSet.getString("photo");
                Categorie categorie = new Categorie(resultSet.getString("categorie"));

                article = new Article(articleId, libelle, marque, prix, photo, categorie);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return article;
    }


    @GET
    @Path("/{idarticle")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticle(@PathParam("idarticle") int idarticle) throws SQLException {
        Article article = getcaracteristique(idarticle);
        return article;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajoutarticle(JAXBElement<Article> article) {
        Article a = article.getValue();
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO article VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, a.getId());
            statement.setString(2, a.getLibelle());
            statement.setString(3, a.getMarque());
            statement.setDouble(4, a.getPrix());
            statement.setString(5, a.getPhoto());
            statement.setString(6, String.valueOf(a.getCat()));
            int resultSet = statement.executeUpdate();
            if(resultSet > 0)
            {
                System.out.println("Nombre de ligne inséré : " + resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return putAndGetResponse(a);
    }

    @PUT
    @Path("/{idarticle}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateArticle(@PathParam("idarticle") int idarticle, Article articlemod) {
        Response res;
        if(contentProvider.containsKey(articlemod.getId())) {
            contentProvider.put(articlemod.getId(), articlemod);
            try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String query = "UPDATE article SET idarticle = ?, libelle =  ?, marque = ?,  prix = ?, photo = ?, " +
                        "categorie = ? WHERE idarticle = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, articlemod.getId());
                statement.setString(2, articlemod.getLibelle());
                statement.setString(3, articlemod.getMarque());
                statement.setDouble(4, articlemod.getPrix());
                statement.setString(5, articlemod.getPhoto());
                statement.setString(6, String.valueOf(articlemod.getCat()));
                statement.setInt(7, idarticle);
                int resultSet = statement.executeUpdate();
                if(resultSet > 0)
                {
                    System.out.println("Nombre de ligne modifiée : " + resultSet);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            res = Response.status(201).entity("Article modifié avec succès").build();
        } else {
            res = Response.noContent().build();
        }

        return res;
    }

    private Response putAndGetResponse(Article article) {
        Response res;
        if(contentProvider.containsKey(article.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.status(201).entity("Article ajouté avec succès").build();
            contentProvider.put(article.getId(), article);
        }

        return res;
    }
}
