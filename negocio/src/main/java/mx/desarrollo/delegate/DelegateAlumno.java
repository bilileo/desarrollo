package mx.desarrollo.delegate;

import mx.desarollo.entity.Alumno;
import mx.desarrollo.integration.ServiceLocator;

public class DelegateAlumno {
    public void saveAlumno(Alumno alumno){
        ServiceLocator.getInstanceAlumnoDAO().save(alumno);
    }

}
