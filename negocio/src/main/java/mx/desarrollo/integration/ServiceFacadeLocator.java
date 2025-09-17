package mx.desarrollo.integration;


import mx.desarollo.entity.Profesor;
import mx.desarrollo.facade.FacadeProfesor;
import mx.desarrollo.facade.FacadeUnidadAprendizaje;
import org.hibernate.query.NativeQuery;

import java.util.List;


public class ServiceFacadeLocator {


    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;
    private static FacadeProfesor facadeProfesor;


    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje;
        }
    }


    // para el profesor
    public static FacadeProfesor getInstanceFacadeProfesor(){
        if(facadeProfesor == null){
            facadeProfesor = new FacadeProfesor();
            return  facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }

}
