package mx.desarrollo.delegate;

import mx.desarollo.entity.Asignado;
import mx.desarollo.entity.AsignadoId;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DelegateAsignacionUnidadProfesor {

    public static byte[] toByteArray(boolean[] booleanArray) {
        if (booleanArray == null) {
            return null;
        }

        int numBytes = (booleanArray.length + 7) / 8;
        byte[] byteArray = new byte[numBytes];

        for (int i = 0; i < booleanArray.length; i++) {
            if (booleanArray[i]) {
                byteArray[i / 8] |= (1 << (7 - (i % 8)));
            }
        }
        return byteArray;
    }

    public static boolean traslape(byte[] arrayProfesor, byte[] arrayNuevo){
        for (int i = 0; i < arrayProfesor.length; i++) {
            if((arrayProfesor[i] & arrayNuevo[i]) == 0){
                continue;
            }
            else{
                return true;
            }
        }
        return false;
    }

    public boolean asignar(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        Optional<Unidadaprendizaje> info = ServiceLocator.getInstanceUnidadAprendizajeDAO().find(idUA);
        List<Asignado> list = ServiceLocator.getInstanceAsignadoDAO().findAll();

        boolean success = true;
        for (Asignado unidad : list) {
            if(unidad.getId().getIdProfesor().equals(idProfesor)) {
                if (traslape(unidad.getLunes(), toByteArray(lunes))) {
                    success = false;
                    break;
                }
                if (traslape(unidad.getMartes(), toByteArray(martes))) {
                    success = false;
                    break;
                }
                if (traslape(unidad.getMiercoles(), toByteArray(miercoles))) {
                    success = false;
                    break;
                }
                if (traslape(unidad.getJueves(), toByteArray(jueves))) {
                    success = false;
                    break;
                }
                if (traslape(unidad.getViernes(), toByteArray(viernes))) {
                    success = false;
                    break;
                }
            }
        }

        Asignado asignado = new Asignado();
        AsignadoId asignadoId = new AsignadoId();
        asignadoId.setIdProfesor(idProfesor);
        asignadoId.setIdUa(idUA);
        asignado.setId(asignadoId);

        asignado.setHrsClase(info.get().getHrsClase());
        asignado.setHrsLab(info.get().getHrsLab());
        asignado.setHrsTaller(info.get().getHrsTaller());

        asignado.setLunes(toByteArray(lunes));
        asignado.setMartes(toByteArray(martes));
        asignado.setMiercoles(toByteArray(miercoles));
        asignado.setJueves(toByteArray(jueves));
        asignado.setViernes(toByteArray(viernes));
        if(success) {
            ServiceLocator.getInstanceAsignadoDAO().asignar(asignado);
        }
        return success;
    }
}
