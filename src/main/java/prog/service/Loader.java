package prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog.model.Role;
import prog.model.User;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Loader {
    @Autowired
    UserService userServices;

    @Autowired
    RoleService roleService;

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
        User user1 = new User("admin", "admin", 33, "admin");
        User user2 = new User("user", "user", 31, "user");
        user1.setRoles(setadmin);
        user2.setRoles(setuser);
        userServices.save(user1);
        userServices.save(user2);
    }

}
