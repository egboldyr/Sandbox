package rest.endpoint;

import api.endpoint.AdministrationApiEndpointInterface;
import api.request.GeneralRequest;
import api.response.GeneralResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EGBoldyr on 10.05.18.
 */

@RestController
@RequestMapping("/administration")
public class AdministrationRestEndpoint implements AdministrationApiEndpointInterface {

    @Override
    @RequestMapping(value = "/createappuser", method = RequestMethod.POST)
    public GeneralResponse<Void> createNewAppUser(@RequestBody GeneralRequest<Void> request) {
        return new GeneralResponse<Void>("200", null);
    }

    @Override
    @RequestMapping(value = "/updateappuser", method = RequestMethod.PUT)
    public GeneralResponse<Void> updateAppUser(@RequestBody GeneralRequest<Void> request) {
        return new GeneralResponse<Void>("200", null);
    }

    @Override
    @RequestMapping(value = "/deleteappuser", method = RequestMethod.DELETE)
    public GeneralResponse<Void> deleteAppUsers(@RequestBody GeneralRequest<Void> request) {
        return new GeneralResponse<Void>("200", null);
    }

    @Override
    @RequestMapping(value = "/getallappusers", method = RequestMethod.GET)
    public GeneralResponse<Void> getAllAppUsers() {
        return new GeneralResponse<Void>("200", null);
    }

}
