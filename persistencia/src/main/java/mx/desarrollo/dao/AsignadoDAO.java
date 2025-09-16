package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mx.desarollo.entity.Asignado;
import mx.desarollo.entity.AsignadoId;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarollo.persistence.AbstractDAO;

import java.util.List;

public class AsignadoDAO extends AbstractDAO<Asignado> {
    private final EntityManager entityManager;

    public AsignadoDAO(EntityManager em) {
        super(Asignado.class);
        this.entityManager = em;
    }

    public void asignar(Asignado asignacion) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.createNativeQuery(
                            "INSERT INTO asignado (Id_profesor, Id_ua,HrsClase,HrsTaller,HrsLab) VALUES (?,?,?,?,?)")
                    .setParameter(1, asignacion.getId().getIdProfesor())
                    .setParameter(2, asignacion.getId().getIdUa())
                    .setParameter(3, asignacion.getHrsClase())
                    .setParameter(4, asignacion.getHrsTaller())
                    .setParameter(5, asignacion.getHrsLab())
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    /*public void eliminar(AsignadoId id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Asignado asignado = entityManager.createQuery(
                            "SELECT a FROM Asignado a WHERE a.id.idProfesor = :idProfesor AND a.id.idUa = :idUA",
                            Asignado.class)
                    .setParameter("idProfesor", id.getIdProfesor())
                    .setParameter("idUA", id.getIdUa())
                    .getSingleResult();

            entityManager.remove(asignado);
            tx.commit();
        } catch (jakarta.persistence.NoResultException e) {
            if (tx.isActive()) tx.rollback();
            throw new IllegalArgumentException(" - La asignacion de existe - ");
        }  catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }*/
    public void eliminar(AsignadoId id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Asignado asignado = entityManager.find(Asignado.class, id);
            if (asignado == null) {
                throw new IllegalArgumentException(" - La asignacion no existe - ");
            }
            entityManager.remove(asignado);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}