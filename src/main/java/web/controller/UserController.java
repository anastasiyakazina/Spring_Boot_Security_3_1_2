package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "admin/user-list")
    public String findAllAdmin(ModelMap model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping(value = "user/user-list_")
    public String findAllUser(Principal currentUser, Model model) {
        User user = userService.findByLastName(currentUser.getName());
        model.addAttribute("users", user);
        return "user/user-list_";
    }

    @GetMapping("admin/user-create")
    public String createUserForm(Model model) {
        Set<Role> uniqueRoles = roleService.findAllRoles();
        model.addAttribute("users", new User());
        model.addAttribute("roles", uniqueRoles);
        return "admin/user-create";
    }

    @PostMapping("admin/user-create")
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/user-list";
    }

    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/user-list";
    }

    @GetMapping("admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("users", userService.findById(id));
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin/user-update";
    }

    @PostMapping("admin/user-update")
    public String updateUser(@ModelAttribute("user") User user,Model model) {
        userService.saveAndFlush(user);
        model.addAttribute("user", user);
        return "redirect:/admin/user-list";
    }
}
