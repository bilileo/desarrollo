package mx.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.desarollo.entity.Administrador;
import mx.desarollo.entity.Unidadaprendizaje;

import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO extends mx.desarollo.persistence.AbstractDAO<Administrador> {
    private final EntityManager entityManager;

    public AdministradorDAO(EntityManager em) {
        super(Administrador.class);
        this.entityManager = em;
    }

    public ArrayList<Administrador> obtenerTodos() {
        List<Administrador> lista = entityManager
                .createQuery("SELECT a FROM Administrador a", Administrador.class)
                .getResultList();
        return new ArrayList<>(lista);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
