package mx.desarrollo.delegate;

import mx.desarollo.entity.Profesor;
import mx.desarrollo.integration.ServiceLocator;

public class DelegateProfesor {
    public void saveProfesor(Profesor profe){
        ServiceLocator.getInstanceProfesorDAO().guardar(profe);
    }
}
