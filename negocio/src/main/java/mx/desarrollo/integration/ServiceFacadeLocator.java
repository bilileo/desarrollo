package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeAdministrador;
import mx.desarrollo.facade.FacadeAsignacionUnidadProfesor;
import mx.desarrollo.facade.FacadeProfesor;
import mx.desarrollo.facade.FacadeUnidadAprendizaje;

public class ServiceFacadeLocator {

    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;
    private static FacadeAsignacionUnidadProfesor facadeAsignacionUnidadProfesor;
    private static FacadeProfesor facadeProfesor;
    private static FacadeAdministrador facadeAdministrador;

    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje;
        }
    }

    public static FacadeAsignacionUnidadProfesor getInstanceFacadeAsignacionUnidadProfesor() {
        if (facadeAsignacionUnidadProfesor == null) {
            facadeAsignacionUnidadProfesor = new FacadeAsignacionUnidadProfesor();
            return facadeAsignacionUnidadProfesor;
        } else {
            return facadeAsignacionUnidadProfesor;
        }
    }

    // para el profesor
    public static FacadeProfesor getInstanceFacadeProfesor() {
        if (facadeProfesor == null) {
            facadeProfesor = new FacadeProfesor();
            return facadeProfesor;
        } else {
            return facadeProfesor;
        }
    }

    public static FacadeAdministrador getInstanceFacadeAdministrador() {
        if (facadeAdministrador == null) {
            facadeAdministrador = new FacadeAdministrador();
            return facadeAdministrador;
        } else {
            return facadeAdministrador;
        }
    }
}
