package rest.endpoint;

import api.dto.UserDTO;
import api.endpoint.AdministrationApiEndpointInterface;
import api.request.GeneralRequest;
import api.request.user.NewUserParameters;
import api.response.GeneralResponse;
import api.response.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.service.AdministrationService;

import javax.validation.Valid;

/**
 * Created by EGBoldyr on 10.05.18.
 */

@RestController
@RequestMapping("/administration")
public class AdministrationRestEndpoint implements AdministrationApiEndpointInterface {

    @Autowired
    private AdministrationService administrationService;

    @Override
    @RequestMapping(value = "/createappuser", method = RequestMethod.POST)
    public GeneralResponse<UserDTO> createNewAppUser(@RequestBody @Valid GeneralRequest<NewUserParameters> request) {
        return new GeneralResponse<UserDTO>(ResponseCode.OK, administrationService.createAppUser(request.getParameters()));
    }

    @Override
    @RequestMapping(value = "/updateappuser", method = RequestMethod.PUT)
    public GeneralResponse<Void> updateAppUser(@RequestBody GeneralRequest<Void> request) {
        return new GeneralResponse<Void>(ResponseCode.OK, null);
    }

    @Override
    @RequestMapping(value = "/deleteappuser", method = RequestMethod.DELETE)
    public GeneralResponse<Void> deleteAppUsers(@RequestBody GeneralRequest<Void> request) {
        return new GeneralResponse<Void>(ResponseCode.OK, null);
    }

    @Override
    @RequestMapping(value = "/getallappusers", method = RequestMethod.GET)
    public GeneralResponse<Void> getAllAppUsers() {
        return new GeneralResponse<Void>(ResponseCode.OK, null);
    }

}