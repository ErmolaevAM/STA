package DAO;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Александр on 05.12.2016.
 */
public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
