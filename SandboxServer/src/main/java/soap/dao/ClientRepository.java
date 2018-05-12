package soap.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import soap.entity.Client;

import java.util.List;

/**
 * Created by EGBoldyr on 12.05.18.
 */

public interface ClientRepository extends JpaRepository<Client, Long> {

    default List<Client> findAll(Integer page, Integer count) {
        return this.findAll(new PageRequest(page, count)).getContent();
    }

}
