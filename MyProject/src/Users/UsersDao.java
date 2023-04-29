package Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

  private final Connection connection;

  public UsersDao(Connection connection) {
    this.connection = connection;
  }

  public void addUser(Users user) throws SQLException {
    String query = "INSERT INTO users (user_name) VALUES (?)";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, user.getUserName());
    statement.executeUpdate();
    statement.close();
  }

  public void updateUser(Users user) throws SQLException {
    String query = "UPDATE users SET user_name = ? WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, user.getUserName());
    statement.setInt(2, user.getId());
    statement.executeUpdate();
    statement.close();
  }

  public void deleteUser(int id) throws SQLException {
    String query = "DELETE FROM users WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    statement.executeUpdate();
    statement.close();
  }

  public Users getUserById(int id) throws SQLException {
    String query = "SELECT * FROM users WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();
    Users user = null;
    if (resultSet.next()) {
      String user_name = resultSet.getString("user_name");
      user = new Users(id, user_name);
    }
    resultSet.close();
    statement.close();
    return user;
  }

  public List<Users> getAllUsers() throws SQLException {
    String query = "SELECT * FROM users";
    PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery();
    List<Users> users = new ArrayList<>();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String user_name = resultSet.getString("user_name");
      Users user = new Users(id, user_name);
      users.add(user);
    }
    resultSet.close();
    statement.close();
    return users;
  }
}
