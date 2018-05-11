package rest.service.impl;

import api.dto.UserDTO;
import api.request.BaseUserItem;
import api.request.user.UserParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.UserDAO;
import rest.domain.User;
import rest.exeption.AdministrationDataException;
import rest.service.AdministrationService;
import rest.util.error.ErrorCode;
import rest.util.mapper.UsersMapper;

import java.util.List;

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
    public UserDTO createAppUser(UserParameters parameters) {
        User user = new User(parameters.getLogin(), parameters.getPassword());
        if (userDAO.findByLogin(parameters.getLogin()) != null ) {
            throw new AdministrationDataException(
                    ErrorCode.LOGIN_ALREADY_EXISTS, "Error! Login already exists!");
        }
        return mapper.userDomainToUserDto(userDAO.saveAndFlush(user));
    }

    @Override
    public UserDTO updateAppUser(UserParameters parameters) {
        User user = userDAO.findByLogin(parameters.getLogin());
        user.setLogin(parameters.getLogin());
        user.setPassword(parameters.getPassword());
        return mapper.userDomainToUserDto(userDAO.saveAndFlush(user));
    }

    @Override
    public void deleteAppUser(BaseUserItem parameters) {
        User user = userDAO.findByLogin(parameters.getLogin());
        if (user != null) {
            userDAO.delete(user);
        }
    }

    @Override
    public List<UserDTO> findAllAppUsers() {
        return mapper.userDomainListToUserDtoList(userDAO.findAll());
    }
}
