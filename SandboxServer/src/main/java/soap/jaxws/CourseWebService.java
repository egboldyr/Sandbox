package soap.jaxws;

import soap.dto.CourseDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CourseWebService {

    @WebMethod
    boolean newCourse(String title);

    @WebMethod
    CourseDTO findByTitle(String title);

    @WebMethod
    CourseDTO[] allCourses();

}
