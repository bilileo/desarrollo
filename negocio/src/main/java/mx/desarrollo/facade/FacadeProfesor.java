package mx.desarrollo.facade;

import mx.desarollo.entity.Profesor;
import mx.desarrollo.delegate.DelegateProfesor;

public class FacadeProfesor {
    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }

    // Mandar prfoesor al delegate
    public void guardarProfesor(Profesor profe){
        delegateProfesor.saveProfesor(profe);
    }
}
