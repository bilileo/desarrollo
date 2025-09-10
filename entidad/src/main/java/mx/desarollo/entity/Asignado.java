package mx.desarollo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asignado")
public class Asignado {
    @EmbeddedId
    private AsignadoId id;

    @MapsId("idProfesor")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id_profesor", nullable = false)
    private Profesor idProfesor;

    @MapsId("idUa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id_ua", nullable = false)
    private Unidadaprendizaje idUa;

    @Column(name = "HrsClase")
    private Byte hrsClase;

    @Column(name = "HrsTaller")
    private Byte hrsTaller;

    @Column(name = "HrsLab")
    private Byte hrsLab;

    public AsignadoId getId() {
        return id;
    }

    public void setId(AsignadoId id) {
        this.id = id;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Unidadaprendizaje getIdUa() {
        return idUa;
    }

    public void setIdUa(Unidadaprendizaje idUa) {
        this.idUa = idUa;
    }

    public Byte getHrsClase() {
        return hrsClase;
    }

    public void setHrsClase(Byte hrsClase) {
        this.hrsClase = hrsClase;
    }

    public Byte getHrsTaller() {
        return hrsTaller;
    }

    public void setHrsTaller(Byte hrsTaller) {
        this.hrsTaller = hrsTaller;
    }

    public Byte getHrsLab() {
        return hrsLab;
    }

    public void setHrsLab(Byte hrsLab) {
        this.hrsLab = hrsLab;
    }

}