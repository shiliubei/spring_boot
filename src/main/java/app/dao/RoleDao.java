package app.dao;

import app.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String roleName);

    List<Role> getAllRoles();
}

