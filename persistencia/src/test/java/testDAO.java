import mx.desarrollo.dao.AlumnoDAO;
import mx.desarrollo.persistence.HibernateUtil;
import mx.desarollo.entity.Alumno;

import java.util.List;

public class testDAO {

    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO(HibernateUtil.getEntityManager());



        for (Alumno alumno : alumnoDAO.findAll()) {
            System.out.println(alumno + "|| id [" + alumno.getId()+ "]");
        }
    }
}
