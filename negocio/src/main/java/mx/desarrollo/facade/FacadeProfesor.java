package mx.desarrollo.facade;

import mx.desarollo.entity.Profesor;
import mx.desarrollo.dao.ProfesorDAO;
import java.util.List;

public class FacadeProfesor {
    private ProfesorDAO profesorDAO = new ProfesorDAO();

    public List<Profesor> listarProfesores() {
        return profesorDAO.obtenerProfesores();
    }
}
