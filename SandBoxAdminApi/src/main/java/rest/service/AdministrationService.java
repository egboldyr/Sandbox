package rest.service;

import api.dto.UserDTO;
import api.request.BaseUserItem;
import api.request.user.UserParameters;

import java.util.List;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public interface AdministrationService {

    UserDTO createAppUser(UserParameters parameters);

    UserDTO updateAppUser(UserParameters parameters);

    void deleteAppUser(BaseUserItem parameters);

    List<UserDTO> findAllAppUsers();

}
