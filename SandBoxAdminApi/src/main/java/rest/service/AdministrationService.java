package rest.service;

import api.dto.UserDTO;
import api.request.user.NewUserParameters;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public interface AdministrationService {

    UserDTO createAppUser(NewUserParameters parameters);


}
