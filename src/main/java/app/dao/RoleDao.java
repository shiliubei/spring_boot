package app.dao;

import app.model.Role;

public interface RoleDao {
    Role getRoleByName(String roleName);
}

