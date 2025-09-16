package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeAdministrador;
import mx.desarrollo.facade.FacadeUnidadAprendizaje;

public class ServiceFacadeLocator {

    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;
    private static FacadeAdministrador facadeAdministrador;

    public static FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
            return facadeUnidadAprendizaje;
        } else {
            return facadeUnidadAprendizaje;
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
