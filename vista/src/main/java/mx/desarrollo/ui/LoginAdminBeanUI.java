package mx.desarrollo.ui;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import mx.desarollo.entity.Administrador;
import mx.desarrollo.helper.LoginHelper;

import java.io.IOException;
import java.io.Serializable;

@Named("loginSAUAP")
@SessionScoped
public class LoginAdminBeanUI implements Serializable {

    private LoginHelper loginHelper;
    private Administrador administrador;

    public LoginAdminBeanUI() {
        loginHelper = new LoginHelper();
    }

    @PostConstruct
    public void init() {
        administrador = new Administrador();
    }

    public void login() throws IOException {
        String appURL = "/indexSAUAP.xhtml";
        Administrador admin = loginHelper.Login(administrador.getMailAdministrador(),
                administrador.getPassAdministrador());

        if (admin != null && admin.getId() != null) {
            administrador = admin;
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + appURL);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario o contraseña incorrecta", "Intente de nuevo"));
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        FacesContext.getCurrentInstance().getExternalContext().redirect(
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/loginSAUAP.xhtml");
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}


