package prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import prog.model.Role;
import prog.model.User;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Loader {
    final
    UserService userServices;

    final
    RoleService roleService;

    public Loader(UserService userServices, RoleService roleService) {
        this.userServices = userServices;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initApiUserData() {
        Set<Role> setadmin = new HashSet<>();
        Set<Role> setuser = new HashSet<>();
        Role role = new Role(1l, "ROLE_USER");
        Role role2 = new Role(2l, "ROLE_ADMIN");
        roleService.addRole(role);
        roleService.addRole(role2);
        setadmin.add(role2);
        setuser.add(role);
        User user1 = new User("admin", "admin@mail.ru", 33, "admin");
        User user2 = new User("user", "user@mail.ru", 31, "user");
        user1.setRoles(setadmin);
        user2.setRoles(setuser);
        userServices.save(user1);
        userServices.save(user2);
    }

}
