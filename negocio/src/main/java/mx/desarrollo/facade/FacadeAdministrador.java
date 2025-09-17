package mx.desarrollo.facade;

import mx.desarollo.entity.Administrador;
import mx.desarrollo.delegate.DelegateAdministrador;

public class FacadeAdministrador {
    private final DelegateAdministrador delegateAdministrador;

    public FacadeAdministrador() {
        this.delegateAdministrador = new DelegateAdministrador();
    }

    public Administrador login(String correo, String password){
        return delegateAdministrador.login(correo, password);
    }
}
