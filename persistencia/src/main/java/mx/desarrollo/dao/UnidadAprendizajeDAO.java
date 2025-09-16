package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarollo.persistence.AbstractDAO;

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

    // eliminar (metodo abstracto)
    public void eliminarUA(int uaID){
        EntityTransaction tx = entityManager.getTransaction();
        try{
            tx.begin();
            //Des-asignar de los profesores
            entityManager.createNativeQuery("DELETE FROM asignado WHERE Id_ua = ?")
                    .setParameter(1,uaID)
                    .executeUpdate();

            // eliminar la ua
            entityManager.createNativeQuery("DELETE FROM unidadaprendizaje WHERE id = ?")
                    .setParameter(1,uaID)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e){
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    // validar si tiene profesores asignados
    public boolean tieneProfesAsignados(int uaID){
        EntityTransaction tx = entityManager.getTransaction();
        try{
            tx.begin();
            Long count = (Long) entityManager.createNativeQuery(
                            "SELECT COUNT(*) FROM asignado WHERE Id_ua = ?")
                    .setParameter(1,uaID)
                    .getSingleResult();
            tx.commit();
            return count > 0;
        } catch(Exception e){
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }
    }

    // validar si la ua existe en la bd
    public boolean existeUA(int uaID){
        EntityTransaction tx = entityManager.getTransaction();
        try{
            Long count = (Long) entityManager.createQuery(
                    "SELECT COUNT(u) FROM Unidadaprendizaje u WHERE u.id = :id")
                    .setParameter("id",uaID)
                    .getSingleResult();
            tx.commit();
            return count>0;

        } catch(Exception e){
            if(tx.isActive()){
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
