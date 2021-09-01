package prog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prog.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl  implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    public UserDaoImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    private final RoleDao roleDao;

    @Override
    @Transactional
    public User findByUsername(String username) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE email=:username").setParameter("username", username).getSingleResult();
    }
    @Override
    @Transactional
    public void save(User user) {

        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User updatedUser) {

        entityManager.merge(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.createQuery("DELETE from User WHERE id=:id").setParameter("id", id).executeUpdate();
  //      entityManager.remove(show(id));

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
        return entityManager.find(User.class, id);
    }
}
