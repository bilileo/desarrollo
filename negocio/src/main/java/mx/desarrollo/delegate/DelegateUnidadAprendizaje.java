package mx.desarrollo.delegate;

import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.Optional;

public class DelegateUnidadAprendizaje {
    public void saveUnidadAprendizaje(Unidadaprendizaje UA){
        ServiceLocator.guardarUnidadAprendizaje(UA);
    }

    public boolean tieneProfeAsignado(int uaID){
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().tieneProfesAsignados(uaID);
    }

    public void eliminarUA(int uaID) throws Exception {
        // el metodo abstracto devuelve optional
        Optional<Unidadaprendizaje> opua = ServiceLocator.getInstanceUnidadAprendizajeDAO().find(uaID);
        // extraer ua
        Unidadaprendizaje ua = opua.orElseThrow(()->new Exception("No se encontró la UA con id: " + uaID));
        // eliminar
        ServiceLocator.getInstanceUnidadAprendizajeDAO().eliminarUA(ua);
    }
}
