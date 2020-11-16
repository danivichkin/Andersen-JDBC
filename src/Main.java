import Entity.Role;
import Entity.User;
import repos.RoleRepos;
import repos.UserRepos;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserRepos userRepos = new UserRepos();
        RoleRepos roleRepos = new RoleRepos();

        User Daniil = new User("Daniil");
        User Ivan = new User("Ivan");
        User Sasha = new User("Sasha");
        User noName = new User("NoName");

        userRepos.create(Daniil);
        userRepos.create(Ivan);
        userRepos.create(Sasha);
        userRepos.create(noName);


        Role j1 = new Role("J1");
        Role j2 = new Role("J2");
        Role j3 = new Role("J3");
        Role M1 = new Role("M1");
        Role M2 = new Role("M2");

        roleRepos.createRole(j1);
        roleRepos.createRole(j2);
        roleRepos.createRole(j3);
        roleRepos.createRole(M1);
        roleRepos.createRole(M2);

        userRepos.addNewRoleToUser(Daniil, j1);
        userRepos.addNewRoleToUser(Daniil, j2);
        userRepos.addNewRoleToUser(Ivan, j1);
        userRepos.addNewRoleToUser(Sasha, M2);

        userRepos.deleteUserByName(Daniil);
    }
}
