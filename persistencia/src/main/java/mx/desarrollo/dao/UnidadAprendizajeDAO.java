package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarollo.persistence.AbstractDAO;

import java.util.ArrayList;
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

    public void modificar(Unidadaprendizaje unidad) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.createNativeQuery(
                            "UPDATE unidadaprendizaje SET Nombre = ?, HrsClase = ?, HrsTaller = ?, HrsLab = ? WHERE id = ?")
                    .setParameter(1, unidad.getNombre())
                    .setParameter(2, unidad.getHrsClase())
                    .setParameter(3, unidad.getHrsTaller())
                    .setParameter(4, unidad.getHrsLab())
                    .setParameter(5, unidad.getId())
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    public ArrayList<Unidadaprendizaje> obtenerTodos() {
        List<Unidadaprendizaje> lista = entityManager
                .createQuery("SELECT ua FROM Unidadaprendizaje ua", Unidadaprendizaje.class)
                .getResultList();
        return new ArrayList<>(lista); // convierte a ArrayList
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
