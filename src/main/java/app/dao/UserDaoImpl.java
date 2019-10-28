package app.dao;

import app.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void addUser(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        user.setRoles(user.getRoles());
        entityManager.persist(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUser(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        user.setRoles(user.getRoles());
        entityManager.merge(user);
    }

    @Override
    public User getUserByLogin(String login) {
        User user1 = null;
        List<User> userList = entityManager.createQuery("from User").getResultList();
        for (User user : userList) {
            if (user.getName().equals(login)) {
                user1 = user;
            }
        }
        return user1;
    }

}
