/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarrollo.ui;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.helper.AltaUnidadaprendizajeHelper;

import java.io.Serializable;

@Named("altaUABeanUI")
@SessionScoped
public class AltaUABeanUI implements Serializable {

    private AltaUnidadaprendizajeHelper guardarHelper;
    private String nombre;
    private Byte hrsClase;
    private Byte hrsTaller;
    private Byte hrsLab;
    Unidadaprendizaje ua;

    public AltaUABeanUI() {
        guardarHelper = new AltaUnidadaprendizajeHelper();
        ua = new Unidadaprendizaje();
    }

    public String limpiar() {
        try {
            this.nombre = null;
            this.hrsClase = null;
            this.hrsTaller = null;
            this.hrsLab = null;

            this.ua = new Unidadaprendizaje();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void alta() {
        try {
            ua.setNombre(nombre);
            ua.setHrsClase(hrsClase);
            ua.setHrsTaller(hrsTaller);
            ua.setHrsLab(hrsLab);

            guardarHelper.AltaUA(ua);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro Exitoso",
                            "UA Registrada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Registro Invalido","Verifica que las Hrs esten en un rango de 0 a 4 hrs..."));
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

}