package app.controller;


import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HelloController {

    private final UserService userService;

    private final RoleService roleService;

    public HelloController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/add")
    public String addUser() {
        return "adminAdd";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/edit")
    public String updateUser(@RequestParam("id") Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user, String role) {

        user.setRoles(makeRolesSet(role));

        userService.updateUser(user);
        return "redirect:/admin/userlist";
    }

    @GetMapping("/admin/userlist")
    public String listCustomers(Model theModel) {
        List<User> usersList = userService.getAllUsers();
        theModel.addAttribute("usersFromServer", usersList);
        return "usersList";
    }

    @PostMapping("/admin/add")
    public String saveUser(@ModelAttribute("user") User user
            , String role
    ) {

        user.setRoles(makeRolesSet(role));

        userService.addUser(user);
        return "redirect:/admin/userlist";
    }

    private Set<Role> makeRolesSet(String role) {
        String[] rolesArray;
        Set<Role> roles = new HashSet<>();
        if (role != null) {
            rolesArray = role.split(",");
            for (String elem : rolesArray) {
                Role userRole = roleService.getRoleByName(elem);
                roles.add(userRole);
            }
        } else {
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }
        return roles;
    }

    @GetMapping("/user")
    public String userPage() {
        return "userPage";
    }

}
