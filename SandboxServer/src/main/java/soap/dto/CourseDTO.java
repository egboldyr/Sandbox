package soap.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by EGBoldyr on 06.04.18.
 */

@XmlRootElement(name = "course")
@XmlType(propOrder = {"id", "title", "clients"})
public class CourseDTO implements Serializable {

    private Long id;

    private String title;

    private Set<ClientDTO> clients;

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
    public Set<ClientDTO> getClients() {
        return clients;
    }
    public void setClients(Set<ClientDTO> clients) {
        this.clients = clients;
    }

}
