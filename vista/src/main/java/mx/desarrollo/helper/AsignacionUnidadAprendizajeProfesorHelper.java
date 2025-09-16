package mx.desarrollo.helper;

import mx.desarrollo.integration.ServiceFacadeLocator;

public class AsignacionUnidadAprendizajeProfesorHelper {
    public int Asignacion(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().asignar(idProfesor,idUA,lunes,martes,miercoles,jueves,viernes);
    }
    public int TotalHorasRequeridas(Integer idUA){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().TotalHorasRequeridas(idUA);
    }
}
