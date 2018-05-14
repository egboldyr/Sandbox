package soap.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by EGBoldyr on 06.04.18.
 */

@XmlRootElement(name = "course")
@XmlType(propOrder = {"id", "title"})
public class CourseDTO implements Serializable {

    private static final long serialVersionUID = -2036198478194555887L;

    private Long id;

    private String title;

    public CourseDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
