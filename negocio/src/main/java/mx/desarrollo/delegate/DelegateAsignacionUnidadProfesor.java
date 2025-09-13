package mx.desarrollo.delegate;

import mx.desarollo.entity.Asignado;
import mx.desarollo.entity.AsignadoId;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.Optional;

public class DelegateAsignacionUnidadProfesor {
    public void asignar(Integer idProfesor, Integer idUA){
        Optional<Unidadaprendizaje> info = ServiceLocator.getInstanceUnidadAprendizajeDAO().find(idUA);
        Asignado asignado = new Asignado();
        AsignadoId asignadoId = new AsignadoId();
        asignadoId.setIdProfesor(idProfesor);
        asignadoId.setIdUa(idUA);
        asignado.setId(asignadoId);
        asignado.setHrsClase(info.get().getHrsClase());
        asignado.setHrsLab(info.get().getHrsLab());
        asignado.setHrsTaller(info.get().getHrsTaller());
        ServiceLocator.getInstanceAsignadoDAO().asignar(asignado);
    }
}
