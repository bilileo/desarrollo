package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarollo.persistence.AbstractDAO;

import java.util.List;

public class UnidadAprendizajeDAO extends AbstractDAO<Unidadaprendizaje> {
    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        super(Unidadaprendizaje.class);
        this.entityManager = em;
    }

    public List<Unidadaprendizaje> obtenerTodos(){
        return entityManager
                .createQuery("SELECT a FROM Alumno a", Unidadaprendizaje.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
