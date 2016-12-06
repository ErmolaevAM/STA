package service;

import model.User;

/**
 * Created by Александр on 05.12.2016.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
