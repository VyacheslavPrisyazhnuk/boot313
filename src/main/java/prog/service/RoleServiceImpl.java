package prog.service;

import org.springframework.stereotype.Service;
import prog.dao.RoleDao;
import prog.model.Role;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public Role show(Long id) {
        return roleDao.show(id);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
