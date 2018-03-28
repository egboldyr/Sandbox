package soap.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @SequenceGenerator(name = "groupId", sequenceName = "seq_group_id", initialValue = 21180000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupId")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name = "courseId", nullable = false)
    private Course courseId;

    @Column
    private String title;

    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Group() {}

    public Group(Course courseId, String title, Date beginDate, Date endDate) {
        this.courseId = courseId;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Course getCourseId() {
        return courseId;
    }
    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
