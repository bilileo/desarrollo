package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mx.desarollo.entity.Asignado;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarollo.persistence.AbstractDAO;

import java.util.List;

public class AsignadoDAO extends AbstractDAO<AsignadoDAO> {
    private final EntityManager entityManager;

    public AsignadoDAO(EntityManager em) {
        super(AsignadoDAO.class);
        this.entityManager = em;
    }

    public void asignar(Asignado asignacion) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(asignacion);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}