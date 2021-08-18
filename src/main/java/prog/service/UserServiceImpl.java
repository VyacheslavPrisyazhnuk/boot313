package prog.service;

import org.springframework.stereotype.Service;
import prog.dao.UserDao;
import prog.model.User;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Override
    public User show(Long id) {
        return userDao.show(id);
    }

}
