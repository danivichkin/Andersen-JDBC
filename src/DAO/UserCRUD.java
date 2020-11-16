package DAO;

import Entity.Role;
import Entity.User;

import java.sql.SQLException;
import java.util.List;


public interface UserCRUD {
    void create(User user) throws SQLException;
    List<User> readAllUsers() throws SQLException;
    List<User> readAllUsersByRole(Role role) throws SQLException;
    void updateUserByName(User user, String newName) throws SQLException;
    void deleteUserByName(User user) throws SQLException;
    void addNewRoleToUser(User user, Role role) throws SQLException;
}
