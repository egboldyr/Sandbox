package soap.dao;

import soap.entity.Client;

import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 */

public interface ClientDAO {

    Long create(Client client);

    Client read(Long id);

    boolean update(Client client);

    boolean delete(Client client);

    List<Client> findClientsPart(Integer from, Integer count);

}
