package app.controller;


import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    private final UserService userService;

    private final RoleService roleService;

    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/rest/userslist")
    public ResponseEntity<List<User>> listCustomers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("/rest/roles")
    public ResponseEntity<List<Role>> rolesSet() {
        List<Role> rolesList = roleService.getAllRoles();
        return ResponseEntity.ok().body(rolesList);
    }

    @GetMapping("/rest/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/rest/userbyname/{name}")
    public UserDetails getUserByName(@PathVariable("name") String name) {
        UserDetails user = userService.getUserByName(name);
        return user;
    }


    @PostMapping(value = "/rest/adduser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> add(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/rest/edituser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> edit(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/rest/deleteuser/{id}")
    @ResponseBody
    public ResponseEntity<User> delete(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
