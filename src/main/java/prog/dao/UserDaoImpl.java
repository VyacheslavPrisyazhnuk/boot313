package prog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prog.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
     //   LoadUsers();
    }


    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public User findByUsername(String username) {
        Query query =
                entityManager.createQuery("SELECT u FROM User u WHERE name=:username");
        query.setParameter("username", username);
        return (User)query.getSingleResult();
    }

//    @Transactional
//    public void LoadUsers() {
//        Set<Role> setadmin = new HashSet<>();
//        Set<Role> setuser = new HashSet<>();
//        setadmin.add(new Role(1l, "ROLE_ADMIN"));
//        setuser.add(new Role(2l, "ROLE_USER"));
//        entityManager.persist(new User("admin", "admin", 33, "admin", setadmin));
//        entityManager.persist(new User("user", "user", 31, "user", setuser));
//    }
    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(Long id, User updatedUser) {

        entityManager.merge(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(show(id));

    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> index() {

        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    @Transactional
    public User show(Long id) {
        Query query =
                entityManager.createQuery("SELECT u FROM User u WHERE id=:id");
        query.setParameter("id", id);
        return (User)query.getSingleResult();
    }
}
