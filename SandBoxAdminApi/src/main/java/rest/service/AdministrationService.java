package rest.service;

import api.dto.UserDTO;
import api.request.BaseUserItem;
import api.request.user.NewUserParameters;

import java.util.List;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public interface AdministrationService {

    UserDTO createAppUser(NewUserParameters parameters);

    void deleteAppUser(BaseUserItem parameters);

    List<UserDTO> findAllAppUsers();

}
