package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarollo.entity.Unidadaprendizaje;

import java.util.List;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegateUnidadAprendizaje;

    public FacadeUnidadAprendizaje() {
        this.delegateUnidadAprendizaje = new DelegateUnidadAprendizaje();
    }

    public void guardarUA(Unidadaprendizaje UA){
        delegateUnidadAprendizaje.saveUnidadAprendizaje(UA);
    }

    public List<Unidadaprendizaje> consultarTodasUA() {
        return delegateUnidadAprendizaje.findAllUA();
    }
}