/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.helper;



import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;

public class UnidadaprendizajeHelper implements Serializable {


    /**
     * Metodo para hacer login llamara a la instancia de usuarioFacade
     * @return
     */

    public void AltaUA(Unidadaprendizaje UA){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().guardarUA(UA);
    }

    public boolean tieneProfeAsignado(int uaID){
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().tieneProfeAsignado(uaID);
    }

    public void ELiminarUA(int uaID) throws Exception{
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminarUA(uaID);
    }
}
