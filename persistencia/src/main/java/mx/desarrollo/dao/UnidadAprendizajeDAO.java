package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.persistence.AbstractDAO;

import java.util.List;

public class UnidadAprendizajeDAO extends AbstractDAO<Unidadaprendizaje> {
    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        super(Unidadaprendizaje.class);
        this.entityManager = em;
    }

    public void guardar(Unidadaprendizaje unidad) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.createNativeQuery(
                            "INSERT INTO unidadaprendizaje (Nombre,HrsClase,HrsTaller,HrsLab) VALUES (?,?,?,?)")
                    .setParameter(1, unidad.getNombre())
                    .setParameter(2, unidad.getHrsClase())
                    .setParameter(3, unidad.getHrsTaller())
                    .setParameter(4, unidad.getHrsLab())
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    /*public void guardar(Unidadaprendizaje unidad) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(unidad);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }*/

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
