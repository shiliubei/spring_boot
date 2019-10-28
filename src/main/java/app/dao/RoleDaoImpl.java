package app.dao;

import app.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private EntityManager entityManager;

    @PersistenceContext
    public  void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role getRoleByName (String roleName) {
        Role role1 = null;
        List<Role> roleList = entityManager.createQuery("from Role").getResultList();
        for(Role role : roleList){
            if(role.getRole().equals(roleName)){
                role1 = role;
            }
        }
        return role1;
    }
}
