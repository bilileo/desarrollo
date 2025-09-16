package mx.desarrollo.helper;

import mx.desarollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;

public class AltaProfesorHelper implements Serializable {
    public void AltaProfesor(Profesor profe){
        ServiceFacadeLocator.getInstanceFacadeProfesor().guardarProfesor(profe);
    }
}
