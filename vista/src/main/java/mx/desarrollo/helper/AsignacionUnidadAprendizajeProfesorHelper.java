package mx.desarrollo.helper;

import mx.desarollo.entity.Asignado;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.util.List;

public class AsignacionUnidadAprendizajeProfesorHelper {
    public int Asignacion(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().asignar(idProfesor,idUA,lunes,martes,miercoles,jueves,viernes);
    }

    public List<Asignado> Consulta(Integer idUA){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().consultar(idUA);
    }

    public boolean[] convert(byte[] byteArray){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().convertir(byteArray);
    }

    public int Modificacion(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().modificar(idProfesor,idUA,lunes,martes,miercoles,jueves,viernes);
    }

    public int TotalHorasRequeridas(Integer idUA){
        return ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().TotalHorasRequeridas(idUA);
    }

    public void eliminarAsignacion(Integer idProfesor, Integer idUA){
        ServiceFacadeLocator.getInstanceFacadeAsignacionUnidadProfesor().eliminar(idProfesor,idUA);
    }
}
