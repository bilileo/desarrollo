package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacionUnidadProfesor;

public class FacadeAsignacionUnidadProfesor {
    private final DelegateAsignacionUnidadProfesor delegateAsignacion;

    public FacadeAsignacionUnidadProfesor() {
        this.delegateAsignacion = new DelegateAsignacionUnidadProfesor();
    }

    public void asignar(Integer idProfesor, Integer idUA) {
        delegateAsignacion.asignar(idProfesor, idUA);
    }
}
