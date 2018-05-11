package rest.util.mapper;

import api.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rest.domain.User;

import java.util.List;

/**
 * Created by EGBoldyr on 11.05.18.
 */

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "id", source = "userId")
    UserDTO userDomainToUserDto(User user);

    List<UserDTO> userDomainListToUserDtoList(List<User> users);

}
