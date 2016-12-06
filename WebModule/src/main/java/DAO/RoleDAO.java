package DAO;

import model.Role;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Александр on 05.12.2016.
 */
public interface RoleDAO extends JpaRepository<Role, Long>{
}
