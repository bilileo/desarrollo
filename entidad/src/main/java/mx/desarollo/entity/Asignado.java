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

    @Column(name = "Lunes")
    private byte[] lunes;

    @Column(name = "Martes")
    private byte[] martes;

    @Column(name = "Miercoles")
    private byte[] miercoles;

    @Column(name = "Jueves")
    private byte[] jueves;

    @Column(name = "Viernes")
    private byte[] viernes;

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

    public byte[] getLunes() {
        return lunes;
    }

    public void setLunes(byte[] lunes) {
        this.lunes = lunes;
    }

    public byte[] getMartes() {
        return martes;
    }

    public void setMartes(byte[] martes) {
        this.martes = martes;
    }

    public byte[] getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(byte[] miercoles) {
        this.miercoles = miercoles;
    }

    public byte[] getJueves() {
        return jueves;
    }

    public void setJueves(byte[] jueves) {
        this.jueves = jueves;
    }

    public byte[] getViernes() {
        return viernes;
    }

    public void setViernes(byte[] viernes) {
        this.viernes = viernes;
    }

}