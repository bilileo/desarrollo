package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mx.desarollo.entity.Profesor;
import mx.desarrollo.persistence.AbstractDAO;

import java.util.List;

public class ProfesorDAO extends AbstractDAO<Profesor> {
    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em) {
        super(Profesor.class);
        this.entityManager = em;
    }

    public void guardar(Profesor profe) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.createNativeQuery(
                            "INSERT INTO profesor (Nombre,ApellidoP,ApellidoM,RFC) VALUES (?,?,?,?)")
                    .setParameter(1, profe.getNombre())
                    .setParameter(2, profe.getApellidoP())
                    .setParameter(3, profe.getApellidoM())
                    .setParameter(4, profe.getRfc())
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }



    // ----- CONSULTAS PROFESOR -----
    public List<Profesor> obtenerProfesoresConUA() {
        List<Profesor> profesores = getEntityManager()
                .createQuery("SELECT DISTINCT p FROM Profesor p " +
                        "LEFT JOIN FETCH p.asignados a " +
                        "LEFT JOIN FETCH a.idUa " +
                        "ORDER BY p.nombre, p.apellidoP, p.apellidoM", Profesor.class)
                .getResultList();

        // Forzar refresh para traer datos nuevos
        for (Profesor p : profesores) {
            getEntityManager().refresh(p);
        }

        return profesores;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
