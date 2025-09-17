package mx.desarrollo.delegate;

import mx.desarollo.entity.Administrador;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class DelegateAdministrador {
    public Administrador login(String correo, String password) {
        List<Administrador> administradores = ServiceLocator.getInstanceAdministradorDAO().obtenerTodos();

        for (Administrador admin : administradores) {
            if (admin.getMailAdministrador().equalsIgnoreCase(correo)
                    && admin.getPassAdministrador().equals(password)) {
                return admin;
            }
        }
        return null;
    }

}
