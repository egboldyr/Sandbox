package soap.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by EGBoldyr on 15.03.18.
 *
 * Сущность "Аккаунт клиента"
 */

@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement(name = "account")
@XmlType(propOrder = {"id", "login", "password", "client" })
public class Account implements Serializable {

    @Id
    @SequenceGenerator(name = "accountId", sequenceName = "seq_account_id", initialValue = 26001000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountId")
    private Long id;

    @NaturalId
    private String login;

    @Column
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    public Account() {}

    public Account(String login, String password, Client client) {
        this.login = login;
        this.password = password;
        this.client = client;
        this.client.setAccount(this);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + id +
                ", login='" + login + '\'' +
                ", client=" + client +
                '}';
    }
}
