/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.helper;

import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class UnidadaprendizajeHelper implements Serializable {


    /**
     * Metodo para hacer login llamara a la instancia de usuarioFacade
     * @return
     */

    public void AltaUA(Unidadaprendizaje UA){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().guardarUA(UA);
    }

    public List<Unidadaprendizaje> consultarTodasUA() {
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().consultarTodasUA();
    }

    public void ModificarUA(Unidadaprendizaje UA){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().modificarUA(UA);
    }

    public ArrayList<Unidadaprendizaje> listaUnidades(){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().obtenerUAS();
    }

    public boolean tieneProfeAsignado(int uaID){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().tieneProfeAsignado(uaID);
    }

    public void eLiminarUA(int uaID) throws Exception{
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminarUA(uaID);
    }

    public boolean existeUA(int uaID) throws Exception {
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().existeUA(uaID);
    }
}
