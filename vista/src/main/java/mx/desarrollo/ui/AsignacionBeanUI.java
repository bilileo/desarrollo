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
import mx.desarollo.entity.Asignado;
import mx.desarrollo.helper.AsignacionUnidadAprendizajeProfesorHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private List<Asignado> profesores;

    public AsignacionBeanUI() {
        asignarHelper = new AsignacionUnidadAprendizajeProfesorHelper();
        lunes = new boolean[24];
        martes = new boolean[24];
        miercoles = new boolean[24];
        jueves = new boolean[24];
        viernes = new boolean[24];
        profesores = new ArrayList<>();
        idProfesor = 0;
        idUA = 0;
    }

    public void consultaUA(){
        profesores = asignarHelper.Consulta(idUA);
    }

    public void consultaProfe(){
        for(Asignado a: profesores){
            if(a.getId().getIdProfesor().equals(idProfesor)){
                lunes = asignarHelper.convert(a.getLunes());
                martes = asignarHelper.convert(a.getMartes());
                miercoles = asignarHelper.convert(a.getMiercoles());
                jueves = asignarHelper.convert(a.getJueves());
                viernes = asignarHelper.convert(a.getViernes());
            }
        }
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
                        int h = asignarHelper.TotalHorasRequeridas(idUA);
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error de asignación",
                                        "Las horas no coinciden con la Unidad de Aprendizaje al asignar, son " + Integer.toString(h) + " horas requeridas"));
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

    public void modificar() {
        try {
            int error = asignarHelper.Modificacion(idProfesor, idUA, lunes, martes, miercoles, jueves, viernes);

            if(error == 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Modificación Exitoso",
                                "Se ha modificado correctamente"));
            }
            else{
                switch(error){
                    case 1:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "No existe la asignación con el ID proporcionado",null));
                        break;
                    case 3:
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error de modificación",
                                        "Posiblemente hay un traslape en el horario del profesor existente"));
                        break;
                    case 4:
                        int h = asignarHelper.TotalHorasRequeridas(idUA);
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error de asignación",
                                        "Las horas no coinciden con la Unidad de Aprendizaje al asignar, son " + Integer.toString(h) + " horas requeridas"));
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

    public void eliminar() {
        try {
            asignarHelper.eliminarAsignacion(idProfesor, idUA);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Eliminación exitosa",
                            "La asignación fue eliminada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No existe asignación con los IDs proporcionados", null));
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
    public List<Asignado> getProfesores(){
        return  profesores;
    }
    public void setProfesores(List<Asignado> asignados){
        this.profesores = asignados;
    }
}

