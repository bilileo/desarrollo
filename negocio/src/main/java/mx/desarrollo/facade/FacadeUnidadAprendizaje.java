package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarollo.entity.Unidadaprendizaje;

import java.util.List;

import java.util.ArrayList;

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
    public void modificarUA(Unidadaprendizaje UA){
        delegateUnidadAprendizaje.modificar(UA);
    }

    public ArrayList<Unidadaprendizaje> obtenerUAS(){
        return delegateUnidadAprendizaje.getListaUnidades();
    }

    public boolean tieneProfeAsignado(int uaID){
        return delegateUnidadAprendizaje.tieneProfeAsignado(uaID);
    }

    public void eliminarUA(int uaID) throws Exception {
        delegateUnidadAprendizaje.eliminarUA(uaID);
    }

    public boolean existeUA(int uaID) throws Exception{
        return delegateUnidadAprendizaje.existeUA(uaID);
    }
}