package soap.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by EGBoldyr on 15.03.18.
 *
 * Сущность "Клиент"
 */

@Entity
@Table(name = "CLIENTS")
@XmlRootElement(name = "client")
@XmlType(propOrder = {"id", "name", "surname", "dateOfBirth", "account"})
public class Client implements Serializable {

    @Id
    @SequenceGenerator(name = "clientId", sequenceName = "seq_client_id", initialValue = 1000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientId")
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;

    public Client() {}

    public Client(String name, String surname, Date dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
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
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
