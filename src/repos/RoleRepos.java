package repos;

import DAO.RoleCRUD;
import Entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepos implements RoleCRUD {

    public static final String INSERT_NEW_ROLE =
            "INSERT INTO ROLES(name) VALUES(?)";

    public static final String READ_ROLES =
            "SELECT * FROM ROLES";

    public static final String UPDATE_USER_BY_NAME =
            "UPDATE ROLES SET NAME = (?) WHERE NAME = (?)";

    public static final String DELETE_USER =
            "DELETE FROM ROLES WHERE NAME = (?)";

    String url = "jdbc:postgresql://localhost/jdbc";
    String username = "postgres";
    String password = "vfrae0v5";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void createRole(Role role) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        preparedStatement = connection.prepareStatement(INSERT_NEW_ROLE);
        preparedStatement.setString(1, role.getName());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public List<Role> readAllRoles() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(READ_ROLES);
        resultSet = preparedStatement.executeQuery();
        List<Role> roles = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            roles.add(new Role(name));
        }
        connection.close();
        return roles;
    }

    @Override
    public void updateByName(Role role, String newRole) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_NAME);
        preparedStatement.setString(1, newRole);
        preparedStatement.setString(2, role.getName());
        preparedStatement.execute();
        connection.close();
    }

    @Override
    public void deleteByName(Role role) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setString(1, role.getName());
        preparedStatement.execute();
        connection.close();
    }
}
