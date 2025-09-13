package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacionUnidadProfesor;

public class FacadeAsignacionUnidadProfesor {
    private final DelegateAsignacionUnidadProfesor delegateAsignacion;

    public FacadeAsignacionUnidadProfesor() {
        this.delegateAsignacion = new DelegateAsignacionUnidadProfesor();
    }

    public void guardarUA(Integer idProfesor, Integer idUA) {
        delegateAsignacion.asignar(idProfesor, idUA);
    }
}
