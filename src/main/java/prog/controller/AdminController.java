package prog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prog.model.Role;
import prog.model.User;
import prog.service.RoleService;
import prog.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("user")
    public String user(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/show.html";
    }

    @GetMapping()
    @Transactional
    public String index(Model model,Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("admin", user);
        model.addAttribute("user", userService.index());
        return "/index.html";
    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.show(id));
//        return "/show.html";
//    }
//
//    @GetMapping("/new")
//    public String newUser(Principal principal, Model model) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        return "/new.html";
//    }
//
//
//
//    @PostMapping("")
//    public String create(@ModelAttribute("user") User user, @RequestParam("my_roles[]") String[] my_roles) {
//
//        Set<Role> roles = new HashSet<>();
//        String rol = new String();
//        for (String my_role : my_roles) {
//            if (my_role.equals("ADMIN")) {
//                rol = "ROLE_ADMIN";
//            } else {
//                rol = "ROLE_USER";
//            }
//        System.out.println(my_role);
//        Role role = new Role((rol.equals("ROLE_ADMIN") ? 2L : 1L), my_role);
//        roles.add(role);
//        }
//        user.setRoles(roles);
////        Set<Role> roles = new HashSet<>();
////        for (String my_role: my_roles) {
////            System.out.println(my_role);
////            Role role = new Role((my_role.equals("ROLE_ADMIN") ? 2L : 1L), my_role);
////            roles.add(role);
////        }
////        user.setRoles(roles);
//        userService.save(user);
//        return "redirect:/admin";
//    }
//
//
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.show(id));
//        return "/edit.html";
//    }
//
//    @PutMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id,
//                         @RequestParam("my_roles[]") String[] my_roles) {
//        Set<Role> roles = new HashSet<>();
//        String rol = new String();
//        for (String my_role: my_roles) {
//            if (my_role.equals("ADMIN")) {
//                rol = "ROLE_ADMIN";
//            } else rol = "ROLE_USER";
//
//            System.out.println(my_role);
//            Role role = new Role((rol.equals("ROLE_ADMIN") ? 2L : 1L), my_role);
//            roles.add(role);
//        }
//        user.setRoles(roles);
//        userService.update(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//     //   model.addAttribute("role", roleService.show(id));
//        userService.delete(id);
//        return "redirect:/admin";
//    }

}
