package mx.desarrollo.facade;

import mx.desarollo.entity.Asignado;
import mx.desarrollo.delegate.DelegateAsignacionUnidadProfesor;

import java.util.List;

public class FacadeAsignacionUnidadProfesor {
    private final DelegateAsignacionUnidadProfesor delegateAsignacion;

    public FacadeAsignacionUnidadProfesor() {
        this.delegateAsignacion = new DelegateAsignacionUnidadProfesor();
    }

    public int asignar(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        return delegateAsignacion.asignar(idProfesor, idUA, lunes, martes, miercoles, jueves, viernes);
    }

    public List<Asignado> consultar(Integer idUA){
        return delegateAsignacion.consultar(idUA);
    }

    public boolean[] convertir(byte[] byteArray){
        return DelegateAsignacionUnidadProfesor.toBooleanArray(byteArray);
    }

    public int modificar(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        return delegateAsignacion.modificar(idProfesor, idUA, lunes, martes, miercoles, jueves, viernes);
    }

    public int TotalHorasRequeridas(Integer idUA){
        return delegateAsignacion.TotalHorasRequeridas(idUA);
    }

    public void eliminar(Integer idProfesor, Integer idUA) { delegateAsignacion.eliminar(idProfesor, idUA); }
}
