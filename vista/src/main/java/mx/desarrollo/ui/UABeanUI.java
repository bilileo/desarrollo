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

@Named("uABeanUI")
@SessionScoped
public class UABeanUI implements Serializable {

    private UnidadaprendizajeHelper uaHelper;
    private String nombre;
    private Byte hrsClase;
    private Byte hrsTaller;
    private Byte hrsLab;
    private int uaID; // para la eliminacion

    public UABeanUI() {
        uaHelper = new UnidadaprendizajeHelper();
    }

    public void alta() {
        try {
            Unidadaprendizaje ua = new Unidadaprendizaje();
            ua.setNombre(nombre);
            ua.setHrsClase(hrsClase);
            ua.setHrsTaller(hrsTaller);
            ua.setHrsLab(hrsLab);

            uaHelper.AltaUA(ua);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro Exitoso",
                            "UA Registrada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error ","Verifica que las horas esten en un rango de 1 a 4 hrs..."));
            e.printStackTrace();
        }
    }

    public void baja(){
        try{
            if(!uaHelper.tieneProfeAsignado(uaID)){
                //No tiene profesores
                bajaDirecta();
            } else{
                // Si tiene profesores
                PrimeFaces.current().executeScript("PF('confirmProfe').show()");
            }
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error","No se puede eliminar la UA: " + e.getMessage()));
        }
    }

    public void bajaDirecta() throws Exception {
        try {
            uaHelper.eLiminarUA(uaID);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito", "UA eliminada correctamente."));
        } catch(Exception w){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error ","No se pudo eliminar la UA: " + w.getMessage()));
            w.printStackTrace();
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

    public int getUaID() {return uaID;}
    public void setUaID(int uaID) {this.uaID = uaID;}
}

