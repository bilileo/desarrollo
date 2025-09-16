package mx.desarrollo.facade;

import mx.desarollo.entity.Administrador;
import mx.desarrollo.delegate.DelegateAdministrador;

public class FacadeAdministrador {
    private final DelegateAdministrador delegateAdministrador;

    public FacadeAdministrador() {
        this.delegateAdministrador = new DelegateAdministrador();
    }

    public Administrador login(String password, String correo){
        return delegateAdministrador.login(password, correo);
    }
}
