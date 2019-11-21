package app.service;

import app.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(String roleName);
    List<Role> getAllRoles();
}


