package mx.desarrollo.delegate;

import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

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




    public boolean tieneProfeAsignado(int uaID){
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().tieneProfesAsignados(uaID);
    }

    public void eliminarUA(int uaID) throws Exception {
        try{
            ServiceLocator.getInstanceUnidadAprendizajeDAO().eliminarUA(uaID);
        } catch (Exception e) {
            throw new Exception("Error al eliminar la UA: " + e.getMessage());
        }
    }

    public boolean existeUA(int uaID) throws Exception {
        try{
            return ServiceLocator.getInstanceUnidadAprendizajeDAO().existeUA(uaID);
        } catch (Exception e) {
            throw new Exception("No se pudo encontrar la UA." + e.getMessage());
        }
    }
}