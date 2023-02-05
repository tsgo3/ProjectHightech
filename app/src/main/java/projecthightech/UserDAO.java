package projecthightech;

import javax.servlet.http.HttpServlet;
import java.sql.*;
public class UserDAO extends HttpServlet {
    private static final String URL ="jdbc:mysql://localhost:3306/hightech";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "190201";

    public Connection connect() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public User login(String mail, String password) throws SQLException {
        User user = null;
        String query = "SELECT * FROM users WHERE mail = ? AND password = ?";
        try(Connection connection = connect())
        {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, mail);
            statement.setString(2, password);
            try (ResultSet resultSet =  statement.executeQuery())
            {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nomU = resultSet.getString("nomUtilisateur");
                    String type = resultSet.getString("type");

                    if(type.equals("client")){
                        user = new Client(id, mail, nomU, password);
                    } else if (type.equals("Admin")) {
                        user = new Admin(id, mail, nomU, password);
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return user;
    }

    public void logout (Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
        }
    }

    public void inscription (User user) throws SQLException{
        if (user instanceof Client){
            String query = "INSERT INTO Client VALUES (?, ?, ?)";
            try(Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getNomUtilisateur());
                statement.setString(2, user.getMail());
                statement.setString(3, user.getPassword());
                statement.executeUpdate();
            }
        } else if(user instanceof Admin) {
            String query = "INSERT INTO Admin VALUES (?, ?, ?)";
            try(Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getNomUtilisateur());
                statement.setString(2, user.getMail());
                statement.setString(3, user.getPassword());
                statement.executeUpdate();
            }
        }
    }
}
