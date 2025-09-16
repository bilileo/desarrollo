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
    private boolean[] lunes;
    private boolean[] martes;
    private boolean[] miercoles;
    private boolean[] jueves;
    private boolean[] viernes;

    public AsignacionBeanUI() {
        asignarHelper = new AsignacionUnidadAprendizajeProfesorHelper();
        lunes = new boolean[24];
        martes = new boolean[24];
        miercoles = new boolean[24];
        jueves = new boolean[24];
        viernes = new boolean[24];
    }

    public void asignar() {
        try {
            int error = asignarHelper.Asignacion(idProfesor, idUA, lunes, martes, miercoles, jueves, viernes);

            if(error == 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Asignacion Exitoso",
                                "Se ha asignado correctamente"));
            }
            else{
                switch(error){
                    case 1:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "No existe la Unidad de aprendizaje con el ID proporcionado",null));
                        break;
                    case 2:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "No existe el profesor con el ID proporcionado",null));
                        break;
                    case 3:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error de asignación",
                                "Posiblemente hay un traslape en el horario del profesor existente"));
                        break;
                    case 4:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error de asignación",
                                        "Las horas no coinciden con la Unidad de Aprendizaje al asignar"));
                        break;
                    case 5:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error duplicación",
                                        "Ya está registrado "));
                        break;

                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error","Favor de llenar los datos correctamente"));
            e.printStackTrace();
        }
    }

    public Integer getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Integer idProfesor) { this.idProfesor = idProfesor; }
    public Integer getIdUA() { return idUA; }
    public void setIdUA(Integer idUA) { this.idUA = idUA; }

    public boolean[] getLunes() { return lunes; }
    public void setLunes(boolean[] lunes) { this.lunes = lunes; }

    public boolean[] getMartes() { return martes; }
    public void setMartes(boolean[] martes) { this.martes = martes; }

    public boolean[] getMiercoles() { return miercoles; }
    public void setMiercoles(boolean[] miercoles) { this.miercoles = miercoles; }

    public boolean[] getJueves() { return jueves; }
    public void setJueves(boolean[] jueves) { this.jueves = jueves; }

    public boolean[] getViernes() { return viernes; }
    public void setViernes(boolean[] viernes) { this.viernes = viernes; }
}

