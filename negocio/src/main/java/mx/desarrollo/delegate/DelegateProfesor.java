package mx.desarrollo.delegate;


import mx.desarollo.entity.Profesor;
import mx.desarrollo.integration.ServiceLocator;
import java.util.List;


public class DelegateProfesor {


    public void saveProfesor(Profesor profe){
        ServiceLocator.getInstanceProfesorDAO().guardar(profe);
    }

    //Agregue metodo para obtener profesores
    public List<Profesor> obtenerProfesoresConUA() {
        return ServiceLocator.getInstanceProfesorDAO().obtenerProfesoresConUA();
    }

}
