package soap.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @SequenceGenerator(name = "courseId", sequenceName = "seq_course_id", initialValue = 20180000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseId")
    private Long id;

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courseId", targetEntity = Group.class)
    private List<Group> groups;

    public Course() {}
    public Course(String title) {
        this.title = title;
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
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
