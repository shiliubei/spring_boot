package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(Integer id);

    User getUser(Integer id);

    void updateUser(User user);

    User getUserByLogin(String login);
}
