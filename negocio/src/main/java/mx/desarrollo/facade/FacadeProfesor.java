package mx.desarrollo.facade;


import mx.desarollo.entity.Profesor;
import mx.desarrollo.delegate.DelegateProfesor;


import java.util.List;


public class FacadeProfesor {
    private final DelegateProfesor delegateProfesor;


    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }


    // Mandar profesor al delegate
    public void guardarProfesor(Profesor profe){
        delegateProfesor.saveProfesor(profe);
    }


    //Lista de profes para consulta
    public List<Profesor> obtenerProfesoresConUA() {
        return delegateProfesor.obtenerProfesoresConUA();
    }




}
