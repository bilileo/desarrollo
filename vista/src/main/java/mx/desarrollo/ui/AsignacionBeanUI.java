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
import mx.desarrollo.helper.AsignacionUnidadAprendizajeProfesorHelper;

import java.io.Serializable;

@Named("asignacionBeanUI")
@SessionScoped
public class AsignacionBeanUI implements Serializable {

    private AsignacionUnidadAprendizajeProfesorHelper asignarHelper;
    private Integer idProfesor;
    private Integer idUA;

    public AsignacionBeanUI() {
        asignarHelper = new AsignacionUnidadAprendizajeProfesorHelper();
    }

    public void asignar() {
        try {
            asignarHelper.Asignacion(idProfesor, idUA);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Asignacion Exitoso",
                            "Se ha asignado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No existe un profesor o Unidad de aprendizaje con el ID proporcionado",null));
            e.printStackTrace();
        }
    }

    public Integer getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Integer idProfesor) { this.idProfesor = idProfesor; }
    public Integer getIdUA() { return idUA; }
    public void setIdUA(Integer idUA) { this.idUA = idUA; }
}

