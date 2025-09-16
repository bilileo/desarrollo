package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarollo.entity.Unidadaprendizaje;

import java.util.ArrayList;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegateUnidadAprendizaje;

    public FacadeUnidadAprendizaje() {
        this.delegateUnidadAprendizaje = new DelegateUnidadAprendizaje();
    }

    public void guardarUA(Unidadaprendizaje UA){
        delegateUnidadAprendizaje.saveUnidadAprendizaje(UA);
    }

    public void modificarUA(Unidadaprendizaje UA){
        delegateUnidadAprendizaje.modificar(UA);
    }

    public ArrayList<Unidadaprendizaje> obtenerUAS(){
        return delegateUnidadAprendizaje.getListaUnidades();
    }

}