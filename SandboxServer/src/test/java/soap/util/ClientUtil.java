package soap.util;

import soap.entity.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EGBoldyr on 02.05.18.
 */

public class ClientUtil {

    public static Client createSingleClientWithoutAccount() {
        Client client = new Client();
        client.setName("Ivan");
        client.setSurname("Ivanov");
        client.setPhone("0975642323");
        client.setEmail("ivanov@gmail.com");
        return client;
    }

    public static List<Client> createClientsList() {
        List<Client> clients = new ArrayList<Client>();

        Client first = new Client();
        first.setName("Ivan");
        first.setSurname("Ivanov");
        first.setPhone("0975642323");
        first.setEmail("ivanov@gmail.com");
        clients.add(first);

        Client second = new Client();
        second.setName("Viktor");
        second.setSurname("Fedorov");
        second.setPhone("0504538978");
        second.setEmail("fedorov@gmail.com");
        clients.add(second);

        Client third = new Client();
        third.setName("Anna");
        third.setSurname("Ahmatova");
        third.setPhone("0632517878");
        third.setEmail("ahmatova_ann@gmail.com");
        clients.add(third);

        return clients;
    }
}
