package app.service;

import app.dao.RoleDao;
import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
