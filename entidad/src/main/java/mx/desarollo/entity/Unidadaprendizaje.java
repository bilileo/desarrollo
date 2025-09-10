package mx.desarollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "unidadaprendizaje")
public class Unidadaprendizaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @ColumnDefault("0")
    @Column(name = "HrsClase")
    private Byte hrsClase;

    @ColumnDefault("0")
    @Column(name = "HrsTaller")
    private Byte hrsTaller;

    @ColumnDefault("0")
    @Column(name = "HrsLab")
    private Byte hrsLab;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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