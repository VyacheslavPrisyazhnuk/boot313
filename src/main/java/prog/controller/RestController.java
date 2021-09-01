package prog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import prog.model.Role;
import prog.model.User;
import prog.service.RoleService;
import prog.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/getAuthorizedUser")
    public ResponseEntity<User> getUserHeader(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserFromID(@PathVariable long id) {
        User user = userService.show(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<User> getUsers() {
        return userService.index();
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user, @Validated @RequestParam(required = false) List<String> roler) {
        Set<Role> roles = new HashSet<>();
        Role role;
        for (String ral: roler) {
            if (ral.contains("ADMIN")) {
                role = new Role(2l,"ROLE_ADMIN");
                roles.add(role);
            } else if (ral.contains("USER")){
                role = new Role(1l, "ROLE_USER");
                roles.add(role);
            } else {
                role = new Role(1l, "ROLE_USER");
                roles.add(role);
            }
        }
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user, @Validated @RequestParam(required = false) List<String> roler) {
        Set<Role> roles = new HashSet<>();
        Role role;
        for (String ral: roler) {
            if (ral.contains("ADMIN")) {
                role = new Role(2l,"ROLE_ADMIN");
                roles.add(role);
            } else if (ral.contains("USER")){
                role = new Role(1l, "ROLE_USER");
                roles.add(role);
            } else {
                role = new Role(1l, "ROLE_USER");
                roles.add(role);
            }
        }
        user.setRoles(roles);
        userService.update(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
