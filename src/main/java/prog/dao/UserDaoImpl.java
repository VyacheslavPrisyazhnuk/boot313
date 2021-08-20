package prog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDaoImpl() {
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
    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
