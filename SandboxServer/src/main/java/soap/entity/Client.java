package soap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 *
 * Сущность "Клиент"
 */

@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable {

    @Id
    @SequenceGenerator(name = "clientId", sequenceName = "seq_client_id", initialValue = 1000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientId")
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;

    @ManyToMany
    @JoinTable(name = "WRITE_DOWN_COURSES",
               joinColumns = @JoinColumn(name = "CLIENT_ID"),
               inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses = new ArrayList<Course>();

    public Client() {}
    public Client(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
