package soap.service;

import soap.entity.Client;

import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 */
public interface ClientService {

    void create(Client client);

    Client read(Long id);

    void update(Client client);

    void delete(Client client);

    Client[] findClientsPart(Integer from, Integer count);
}
