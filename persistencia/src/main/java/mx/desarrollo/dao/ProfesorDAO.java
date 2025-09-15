package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import mx.desarollo.entity.Profesor;
import java.util.List;

public class ProfesorDAO {

    @PersistenceContext(unitName = "desarrolloPU")
    private EntityManager em;

    // Metodo para consultar a todos los profesores ordenados por nombre
    public List<Profesor> obtenerProfesores() {
        String jpql = "SELECT p FROM Profesor p ORDER BY p.nombre";
        TypedQuery<Profesor> query = em.createQuery(jpql, Profesor.class);
        return query.getResultList();
    }
}
