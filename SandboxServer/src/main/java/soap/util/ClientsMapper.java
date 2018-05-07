package soap.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import soap.dto.ClientDTO;
import soap.entity.Client;

/**
 * Created by EGBoldyr on 07.05.18.
 */

@Mapper(componentModel = "spring")
public interface ClientsMapper {

    @Mapping(target = "account", source = "account.login")
    ClientDTO clientToClientDto(Client client);

}
