package mx.desarollo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @Column(name = "id_Administrador", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "mail_Administrador", length = 45)
    private String mailAdministrador;

    @Size(max = 45)
    @Column(name = "pass_Administrador", length = 45)
    private String passAdministrador;

    @Size(max = 45)
    @Column(name = "user_Administrador", length = 45)
    private String userAdministrador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailAdministrador() {
        return mailAdministrador;
    }

    public void setMailAdministrador(String mailAdministrador) {
        this.mailAdministrador = mailAdministrador;
    }

    public String getPassAdministrador() {
        return passAdministrador;
    }

    public void setPassAdministrador(String passAdministrador) {
        this.passAdministrador = passAdministrador;
    }

    public String getUserAdministrador() {
        return userAdministrador;
    }

    public void setUserAdministrador(String userAdministrador) {
        this.userAdministrador = userAdministrador;
    }

}