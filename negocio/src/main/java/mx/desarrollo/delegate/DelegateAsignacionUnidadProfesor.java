package mx.desarrollo.delegate;

import mx.desarollo.entity.Asignado;
import mx.desarollo.entity.AsignadoId;
import mx.desarollo.entity.Profesor;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.integration.ServiceLocator;

import java.util.ArrayList;
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
    public static boolean[] toBooleanArray(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        boolean[] booleanArray = new boolean[byteArray.length * 8];

        for (int i = 0; i < byteArray.length; i++) {
            byte currentByte = byteArray[i];
            for (int bitIndex = 0; bitIndex < 8; bitIndex++) {
                booleanArray[i * 8 + bitIndex] = ((currentByte >> bitIndex) & 1) != 0;
            }
        }
        return booleanArray;
    }

    public List<Asignado> consultar(Integer idUA) {
        List<Asignado> all = ServiceLocator.getInstanceAsignadoDAO().findAll();
        List<Asignado> filtered = new ArrayList<>();
        for(Asignado asignado : all) {
            if(asignado.getId().getIdUa().equals(idUA)) {
                filtered.add(asignado);
            }
        }
        return filtered;
    }

    public int TotalHorasRequeridas(Integer idUA) {
        Optional<Unidadaprendizaje> info = ServiceLocator.getInstanceUnidadAprendizajeDAO().find(idUA);
        if (info.isPresent()) {
            return (int)info.get().getHrsClase() + (int)info.get().getHrsLab() + (int)info.get().getHrsTaller();
        }
        return 0;
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

    public static int booleanTrue(boolean[] booleans){
        int total = 0;
        for (int i = 0; i < booleans.length; i++) {
            if(booleans[i] == true){
                total++;
            }
        }
        return total;
    }

    public int modificar(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        List<Asignado> list = ServiceLocator.getInstanceAsignadoDAO().findAll();
        Asignado encontrado = null;
        for (Asignado unidad : list) {
            if(unidad.getId().getIdProfesor().equals(idProfesor) && unidad.getId().getIdUa().equals(idUA)){
                encontrado = unidad;
                break;
            }
        }

        if(encontrado == null){
            return 1;
        }

        int total = (int)encontrado.getHrsClase() + (int)encontrado.getHrsLab() + (int)encontrado.getHrsTaller();
        int totalHoras = booleanTrue(lunes) + booleanTrue(martes) + booleanTrue(miercoles) + booleanTrue(jueves) + booleanTrue(viernes);

        if(total != totalHoras){
            return 4;
        }

        boolean success = true;
        for (Asignado unidad : list) {
            if(unidad != encontrado) {
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
        }

        if(!success){
            return 3; //Traslape de horario
        }

        encontrado.setLunes(toByteArray(lunes));
        encontrado.setMartes(toByteArray(martes));
        encontrado.setMiercoles(toByteArray(miercoles));
        encontrado.setJueves(toByteArray(jueves));
        encontrado.setViernes(toByteArray(viernes));

        ServiceLocator.getInstanceAsignadoDAO().modificar(encontrado);
        return 0;
    }

    public int asignar(Integer idProfesor, Integer idUA, boolean[] lunes, boolean[] martes, boolean[] miercoles, boolean[] jueves, boolean[] viernes){
        Optional<Unidadaprendizaje> info = ServiceLocator.getInstanceUnidadAprendizajeDAO().find(idUA);
        Optional<Profesor> profe = ServiceLocator.getInstanceProfesorDAO().find(idProfesor);
        List<Asignado> list = ServiceLocator.getInstanceAsignadoDAO().findAll();

        if(info.isEmpty()){
            return 1; //no existe UA
        }

        if(profe.isEmpty()){
            return 2; //no existe profesor
        }

        for (Asignado unidad : list) {
            AsignadoId id = unidad.getId();
            if(id.getIdProfesor().equals(idProfesor) && id.getIdUa().equals(idUA)){
                return 5;
            }
        }

        int total = (int)info.get().getHrsClase() + (int)info.get().getHrsLab() + (int)info.get().getHrsTaller();
        int totalHoras = booleanTrue(lunes) + booleanTrue(martes) + booleanTrue(miercoles) + booleanTrue(jueves) + booleanTrue(viernes);

        if(total != totalHoras){
            return 4;
        }

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

        if(!success){
            return 3; //Traslape de horario
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

        ServiceLocator.getInstanceAsignadoDAO().asignar(asignado);
        return 0;
    }

    public void eliminar(Integer idProfesor, Integer idUA) {
        AsignadoId asignadoId = new AsignadoId();
        asignadoId.setIdProfesor(idProfesor);
        asignadoId.setIdUa(idUA);
        ServiceLocator.getInstanceAsignadoDAO().eliminar(asignadoId);
    }
}
