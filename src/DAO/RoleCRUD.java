package DAO;

import Entity.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleCRUD {
    void createRole(Role role) throws SQLException;
    List<Role> readAllRoles() throws SQLException;
    void updateByName(Role role, String newRole) throws SQLException;
    void deleteByName(Role role) throws SQLException;
}
