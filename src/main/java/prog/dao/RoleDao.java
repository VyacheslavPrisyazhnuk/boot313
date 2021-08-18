package prog.dao;


import prog.model.Role;

public interface RoleDao {
    void addRole(Role role);
    Role show(Long id);
    Role findByName(String name);
//    Set<Role> findByUser(User user);
}
