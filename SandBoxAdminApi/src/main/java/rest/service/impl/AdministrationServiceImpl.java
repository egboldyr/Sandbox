package rest.service.impl;

import api.dto.UserDTO;
import api.request.user.NewUserParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.UserDAO;
import rest.domain.User;
import rest.service.AdministrationService;
import rest.util.mapper.UsersMapper;

/**
 * Created by EGBoldyr on 11.05.18.
 */

@Service
public class AdministrationServiceImpl implements AdministrationService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UsersMapper mapper;

    @Override
    public UserDTO createAppUser(NewUserParameters parameters) {
        User user = new User(parameters.getLogin(), parameters.getPassword());
        return mapper.userDomainToUserDto(userDAO.saveAndFlush(user));
    }
}
