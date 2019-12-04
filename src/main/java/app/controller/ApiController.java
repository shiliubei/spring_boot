package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private final UserService userService;
    private final RoleService roleService;

    public ApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/rest/userbyname/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/rest/userslist")
    public List<User> listCustomers() {
        return userService.getAllUsers();
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

    @PostMapping(value = "/rest/adduser")
    @ResponseBody
    public ResponseEntity<User> add(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/rest/edituser")
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
