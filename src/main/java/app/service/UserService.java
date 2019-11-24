package app.service;

import app.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(Integer id);

    User getUser(Integer id);

    User getUserByName(String name);

    void updateUser(User user);

}
