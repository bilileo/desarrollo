package mx.desarrollo.helper;


import mx.desarollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;


import java.io.Serializable;
import java.util.List;


public class ProfesorHelper implements Serializable {
    public void AltaProfesor(Profesor profe){
        ServiceFacadeLocator.getInstanceFacadeProfesor().guardarProfesor(profe);
    }


    //metodo para la consulta
    public List<Profesor> obtenerProfesoresConUA() {
        return ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerProfesoresConUA();
    }


}
