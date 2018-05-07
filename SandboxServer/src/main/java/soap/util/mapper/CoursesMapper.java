package soap.util.mapper;

import org.mapstruct.Mapper;
import soap.dto.CourseDTO;
import soap.entity.Course;

import java.util.Collection;

/**
 * Created by EGBoldyr on 07.05.18.
 */

@Mapper(componentModel = "spring")
public interface CoursesMapper {

    CourseDTO courseToCourseDto(Course course);

    CourseDTO[] coursesArrayToCourseDtoArray(Course[] courses);

    CourseDTO[] coursesListToCourseDtoArray(Collection<Course> courses);

}
