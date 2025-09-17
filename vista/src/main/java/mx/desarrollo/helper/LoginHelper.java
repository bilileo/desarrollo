package mx.desarrollo.helper;
import mx.desarollo.entity.Administrador;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;

public class LoginHelper implements Serializable {
    /**
     * Metodo para hacer login llamara a la instancia de usuarioFacade
     * @param correo
     * @param password
     * @return
     */
    public Administrador Login(String correo, String password){
        return ServiceFacadeLocator.getInstanceFacadeAdministrador().login(correo, password);
    }
}
