package mx.desarrollo.delegate;

import mx.desarollo.entity.Administrador;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class DelegateAdministrador {
    public Administrador login(String password, String correo){
        Administrador administrador = new Administrador();
        List<Administrador> administradores = ServiceLocator.getInstanceAdministradorDAO().findAll();

        for(Administrador admin:administradores){
            if(admin.getPassAdministrador().equalsIgnoreCase(password) && admin.getMailAdministrador().equalsIgnoreCase(correo)){
                administrador = admin;
            }
        }
        return administrador;
    }
}
