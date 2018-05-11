package api.endpoint;

import api.dto.UserDTO;
import api.request.BaseUserItem;
import api.request.GeneralRequest;
import api.request.user.NewUserParameters;
import api.response.GeneralResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * Created by EGBoldyr on 10.05.18.
 */

@SwaggerDefinition(
        info = @Info(description = "Сервис для администрирования учетных записей",
                     version = "0.0.1",
                     title = "Administration API"),
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTPS})
@Api(tags = {"Администрирование"}, produces = "application/json", protocols = "https")
public interface AdministrationApiEndpointInterface {

    @ApiOperation(value = "Создание новой учетной записи пользователя для приложения")
    GeneralResponse<UserDTO> createNewAppUser(GeneralRequest<NewUserParameters> request);

    @ApiOperation(value = "Обновление данных в учетной записи пользователя приложения")
    GeneralResponse<Void> updateAppUser(GeneralRequest<Void> request);

    @ApiOperation(value = "Удаление учетной записи пользователя приложения")
    GeneralResponse<Void> deleteAppUsers(GeneralRequest<BaseUserItem> request);

    @ApiOperation(value = "Получение списка всех существующих учетных записей")
    GeneralResponse<Void> getAllAppUsers();

}
