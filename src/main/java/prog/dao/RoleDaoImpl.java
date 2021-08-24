package prog.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prog.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role show(Long id) {
        return entityManager.find(Role.class, id);
//        Query query =
//                entityManager.createQuery("SELECT r FROM Role r WHERE id=:id");
//        query.setParameter("id", id);
//        return (Role)query.getSingleResult();
    }

    @Override
    public Role findByName(String name) {
           return  (Role)entityManager.createQuery("SELECT r FROM Role r WHERE name=:name").setParameter("name", name).getSingleResult();
    }
}
