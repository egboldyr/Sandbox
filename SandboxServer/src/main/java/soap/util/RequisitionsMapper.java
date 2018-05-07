package soap.util;

import org.mapstruct.Mapper;
import soap.dto.RequisitionDTO;
import soap.entity.Requisition;

/**
 * Created by EGBoldyr on 07.05.18.
 */

@Mapper(componentModel = "spring")
public interface RequisitionsMapper {

    RequisitionDTO requisitionToRequisitionDto(Requisition requisition);

    RequisitionDTO[] requitionsArrayToRequisitionDtoArray(Requisition[] requisitions);
}
