package mx.desarrollo.delegate;

import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class DelegateUnidadAprendizaje {
    public void saveUnidadAprendizaje(Unidadaprendizaje UA){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().guardar(UA);
    }

    public List<Unidadaprendizaje> findAllUA(){
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();
    }
    public void modificar(Unidadaprendizaje ua){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().modificar(ua);
    }

    public ArrayList<Unidadaprendizaje> getListaUnidades(){
        ArrayList<Unidadaprendizaje> Unidades = ServiceLocator.getInstanceUnidadAprendizajeDAO().obtenerTodos();
        return Unidades;
    }




}
