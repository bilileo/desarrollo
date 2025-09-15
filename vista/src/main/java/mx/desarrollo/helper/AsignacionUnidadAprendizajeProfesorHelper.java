package mx.desarrollo.helper;

import mx.desarrollo.integration.ServiceFacadeLocator;

public class AsignacionUnidadAprendizajeProfesorHelper {
    public void Asignacion(Integer idProfesor, Integer idUA){
        ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().asignar(idProfesor,idUA);
    }
}
