package api.endpoint;

import api.request.GeneralRequest;
import api.response.GeneralResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * Created by EGBoldyr on 10.05.18.
 */

@SwaggerDefinition(
        info = @Info(description = "Operations for administration", version = "0.0.1", title = "Administration API"),
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTPS})
@Api(tags = {"Administration"}, produces = "application/json", protocols = "https")
public interface AdministrationApiEndpointInterface {

    @ApiOperation(value = "Create new APP-User")
    public GeneralResponse<Void> createNewApplicationUser(GeneralRequest<Void> request);
}
