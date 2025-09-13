package mx.desarrollo.delegate;

import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

public class DelegateUnidadAprendizaje {
    public void saveUnidadAprendizaje(Unidadaprendizaje UA){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().guardar(UA);
    }
}
