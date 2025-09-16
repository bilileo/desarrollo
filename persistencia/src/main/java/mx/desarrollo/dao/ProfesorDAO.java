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


   /*public void guardar(Profesor profe) {
       EntityTransaction tx = entityManager.getTransaction();
       try {
           tx.begin();
           entityManager.persist(profe);
           tx.commit();
       } catch (Exception e) {
           if (tx.isActive()) tx.rollback();
           throw e;
       }
   }*/


    // ----- CONSULTAS PROFESOR -----
    //commit agregue metodo para obtener los profesores ordenados con UA
    public List<Profesor> obtenerProfesoresConUA() {
        return getEntityManager()
                .createQuery("SELECT p FROM Profesor p ORDER BY p.nombre, p.apellidoP, p.apellidoM", Profesor.class)
                .getResultList();
    }



    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
