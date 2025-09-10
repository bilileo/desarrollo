package mx.desarollo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AsignadoId implements Serializable {
    private static final long serialVersionUID = 8008712819156921354L;
    @NotNull
    @Column(name = "Id_profesor", nullable = false)
    private Integer idProfesor;

    @NotNull
    @Column(name = "Id_ua", nullable = false)
    private Integer idUa;

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdUa() {
        return idUa;
    }

    public void setIdUa(Integer idUa) {
        this.idUa = idUa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AsignadoId entity = (AsignadoId) o;
        return Objects.equals(this.idProfesor, entity.idProfesor) &&
                Objects.equals(this.idUa, entity.idUa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfesor, idUa);
    }

}