package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacionUnidadProfesor;

public class FacadeAsignacionUnidadProfesor {
    private final DelegateAsignacionUnidadProfesor delegateAsignacion;

    public FacadeAsignacionUnidadProfesor() {
        this.delegateAsignacion = new DelegateAsignacionUnidadProfesor();
    }

    public int asignar(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        return delegateAsignacion.asignar(idProfesor, idUA, lunes, martes, miercoles, jueves, viernes);
    }
}
