package repos;

import DAO.UserCRUD;
import Entity.Role;
import Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepos implements UserCRUD {

    public static final String INSERT_NEW_USER =
            "INSERT INTO USR(name) VALUES (?)";

    public static final String READ_USER =
            "SELECT * FROM USR";

    public static final String UPDATE_USER_BY_NAME =
            "UPDATE USR SET name = (?) WHERE name = (?)";

    public static final String DELETE_USER =
            "DELETE FROM USR WHERE NAME = (?)";

    public static final String ADD_USER_ROLE =
            "INSERT INTO USR_ROLE(id_user, id_roles)  " +
                    "SELECT usr.id, roles.id " +
                    "FROM usr, roles " +
                    "where usr.name = (?) and roles.name = (?)";

    public static final String READ_ALL_USERS_BY_ROLE =
            "select usr.name from usr " +
                    "inner join usr_role ur on usr.id = ur.id_user " +
                    "inner join roles r on r.id = ur.id_roles " +
                    "where r.name = (?);";

    String url = "jdbc:postgresql://localhost/jdbc";
    String username = "postgres";
    String password = "vfrae0v5";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void create(User user) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public List<User> readAllUsers() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(READ_USER);
        resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            users.add(new User(name));
        }
        connection.close();
        return users;
    }

    @Override
    public List<User> readAllUsersByRole(Role role) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_USERS_BY_ROLE);
        resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            users.add(new User(name));
        }
        connection.close();
        return users;
    }

    @Override
    public void updateUserByName(User user, String newName) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_NAME);
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, user.getName());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void deleteUserByName(User user) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void addNewRoleToUser(User user, Role role) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_ROLE);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, role.getName());
        preparedStatement.execute();
        connection.close();
    }
}
