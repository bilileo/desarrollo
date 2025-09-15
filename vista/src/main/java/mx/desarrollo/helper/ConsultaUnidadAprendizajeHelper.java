package mx.desarrollo.helper;

import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;
import java.util.List;

public class ConsultaUnidadAprendizajeHelper implements Serializable {
    public List<Unidadaprendizaje> consultarTodasUA() {
        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().consultarTodasUA();
    }
}
