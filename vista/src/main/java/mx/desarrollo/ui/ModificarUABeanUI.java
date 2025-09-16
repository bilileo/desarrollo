package mx.desarrollo.ui;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.helper.UnidadaprendizajeHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("modificarUABeanUI")
@SessionScoped
public class ModificarUABeanUI implements Serializable {

    UnidadaprendizajeHelper modifcarHelper = new UnidadaprendizajeHelper();
    private String nombreSeleccionado;
    private ArrayList<Unidadaprendizaje> listaUnidades;


    private String nombre;
    private Byte hrsClase;
    private Byte hrsTaller;
    private Byte hrsLab;

    private Unidadaprendizaje unidadaprendizaje = new Unidadaprendizaje();

    @PostConstruct
    public void init() {
        listaUnidades = new ArrayList<>();
        listaUnidades = modifcarHelper.listaUnidades();
    }

    public void llenarCamposUA() {
        for (Unidadaprendizaje unidad : listaUnidades) {
            if (unidad.getNombre().equals(getNombreSeleccionado())) {
                unidadaprendizaje = unidad;
                break;
            }
        }

        this.nombre = unidadaprendizaje.getNombre();
        this.hrsClase = unidadaprendizaje.getHrsClase();
        this.hrsTaller = unidadaprendizaje.getHrsTaller();
        this.hrsLab = unidadaprendizaje.getHrsLab();
    }

    public void modificarUA() {
        try {
            llenarCamposUA();
            unidadaprendizaje.setNombre(nombre);
            unidadaprendizaje.setHrsClase(hrsClase);
            unidadaprendizaje.setHrsTaller(hrsTaller);
            unidadaprendizaje.setHrsLab(hrsLab);

            modifcarHelper.ModificarUA(unidadaprendizaje);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Modificación Exitosa",
                            "UA Modificada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Modificación Invalida","Verifica que las Hrs esten en un rango de 0 a 4 hrs..."));
        }
    }

    public List<Unidadaprendizaje> getUnidades() {
        return listaUnidades;
    }
    public String getNombreSeleccionado() {
        return nombreSeleccionado;
    }
    public void setNombreSeleccionado(String nombreSeleccionado) {
        this.nombreSeleccionado = nombreSeleccionado;
    }
    public Unidadaprendizaje getUnidadaprendizaje() {
        return unidadaprendizaje;
    }
    public void setUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
        this.unidadaprendizaje = unidadaprendizaje;
    }
}



