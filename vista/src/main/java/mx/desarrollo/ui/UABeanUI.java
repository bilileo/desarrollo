/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.ui;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.helper.UnidadaprendizajeHelper;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@Named("UABeanUI")
@SessionScoped
public class UABeanUI implements Serializable {

    private UnidadaprendizajeHelper uaHelper;
    private String nombre;
    private Byte hrsClase;
    private Byte hrsTaller;
    private Byte hrsLab;
    Unidadaprendizaje ua;
    Unidadaprendizaje unidadaprendizaje;
    private UnidadaprendizajeHelper consultaHelper;
    private List<Unidadaprendizaje> listaUA;
    private int uaID; // para la eliminacion

    public UABeanUI() {
        uaHelper = new UnidadaprendizajeHelper();
        unidadaprendizaje = new Unidadaprendizaje();
        consultaHelper = new UnidadaprendizajeHelper();
        ua = new Unidadaprendizaje();
        cargarUA();
    }

    public String limpiar() {
        try {
            this.nombre = null;
            this.hrsClase = null;
            this.hrsTaller = null;
            this.hrsLab = null;

            this.unidadaprendizaje = new Unidadaprendizaje();
        } catch (Exception e) {
            return null;
        }
        return null;
        uaHelper = new UnidadaprendizajeHelper();
    }

    public void alta() {
        try {
            unidadaprendizaje.setNombre(nombre);
            unidadaprendizaje.setHrsClase(hrsClase);
            unidadaprendizaje.setHrsTaller(hrsTaller);
            unidadaprendizaje.setHrsLab(hrsLab);

            uaHelper.AltaUA(unidadaprendizaje);

            cargarUA();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro Exitoso",
                            "UA Registrada"));

            limpiar();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Registro Invalido","Verifica que las Hrs esten en un rango de 0 a 4 hrs..."));
                            "Error ","Verifica que las horas esten en un rango de 1 a 4 hrs..."));
            e.printStackTrace();
        }
    }

    // primera confirmación para eliminar
    public void validarYConfirmar(){
        try{
            // si no existe
            if(!uaHelper.existeUA(uaID)){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "No existe.","La UA con id: " + uaID + " no se encontró."));
                return;
            }
            // si existe pedir confirmacion
            PrimeFaces.current().executeScript("PF('confirmar').show()");
        } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error.",e.getMessage()));
        }
    }

    // segunda confirmacion si la ua tiene profesores
    public void confirmarEliminacion(){
        try{
            // si la ua existe
            if(!uaHelper.tieneProfeAsignado(uaID)){
                // si no tiene profes eliminar directamente
                eliminarUA();
            } else {
                // si tiene profesores volver a preguntar
                PrimeFaces.current().executeScript("PF('confirmProfe').show()");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",e.getMessage()));
        }
    }

    // eliminacion directa
    public void eliminarUA(){
        try{
            uaHelper.eLiminarUA(uaID);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Éxito","UA eliminada correctamente."));
            uaID = 0;
            PrimeFaces.current().ajax().update("formBajaUA:uaIDInput");
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error","No se pudo eliminar la UA: " + e.getMessage()));
        }

    }

    // getters y setters de nombre, hrsClase, hrsTaller, hrsLab obligatorios!!!
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Byte getHrsClase() { return hrsClase; }
    public void setHrsClase(Byte hrsClase) { this.hrsClase = hrsClase; }

    public Byte getHrsTaller() { return hrsTaller; }
    public void setHrsTaller(Byte hrsTaller) { this.hrsTaller = hrsTaller; }

    public Byte getHrsLab() { return hrsLab; }
    public void setHrsLab(Byte hrsLab) { this.hrsLab = hrsLab; }

    public Unidadaprendizaje getUnidadaprendizaje() {
        return unidadaprendizaje;
    }

    public void setUnidadaprendizaje(Unidadaprendizaje unidadaprendizaje) {
        this.unidadaprendizaje = unidadaprendizaje;
    }
    /*public void consultaUABeanUI() {
        consultaHelper = new UnidadaprendizajeHelper();
        cargarUA();
    }*/
    private void cargarUA() {
        listaUA = consultaHelper.consultarTodasUA();
    }
    public List<Unidadaprendizaje> getListaUA() {
        return listaUA;
    }

}

    public int getUaID() {return uaID;}
    public void setUaID(int uaID) {this.uaID = uaID;}
}

