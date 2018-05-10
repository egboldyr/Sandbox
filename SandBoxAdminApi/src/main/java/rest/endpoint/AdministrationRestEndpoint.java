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
    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public GeneralResponse<Void> createNewApplicationUser(@RequestBody GeneralRequest<Void> request) {
        return new GeneralResponse<Void>("200", null);
    }
}
