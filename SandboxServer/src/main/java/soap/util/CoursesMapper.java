package soap.util;

import org.mapstruct.Mapper;
import soap.dto.CourseDTO;
import soap.entity.Course;

/**
 * Created by EGBoldyr on 07.05.18.
 */

@Mapper(componentModel = "spring")
public interface CoursesMapper {

    CourseDTO courseToCourseDto(Course course);

}
