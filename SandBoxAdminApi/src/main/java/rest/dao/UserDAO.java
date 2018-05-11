package rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.domain.User;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public interface UserDAO extends JpaRepository<User, Long> {

}
