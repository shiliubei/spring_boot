package app.controller;


import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {

    private final UserService userService;

    private final RoleService roleService;

    public HelloController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/userslist")
    public String webPage (Model theModel1) {
//        List<User> usersList = userService.getAllUsers();
//        theModel1.addAttribute("usersFromServer", usersList);
        return "adminUsersListRest";
    }

    @GetMapping("/user")
    public String userPage() {
        return "userPage";
    }

}
