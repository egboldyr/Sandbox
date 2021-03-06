package soap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Entity
@Table(name = "COURSES")
public class Course implements Serializable {

    @Id
    @SequenceGenerator(name = "courseId", sequenceName = "seq_course_id", initialValue = 20180000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseId")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "courseId", targetEntity = Group.class)
    private List<Group> groups = new ArrayList<Group>();

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, targetEntity = Client.class)
    private List<Client> clients = new ArrayList<Client>();

    public Course() {}

    public Course(String title) {
        this.title = title;
    }

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    public List<Client> getClients() {
        return clients;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
