package mx.desarrollo.ui;

import jakarta.ejb.Local;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarollo.entity.Profesor;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.helper.AltaProfesorHelper;

import javax.xml.validation.Validator;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named("altaProfeBeanUI")
@ViewScoped

public class AltaProfeBeanUI implements Serializable {
    private AltaProfesorHelper guardarHelper;
    private Profesor profe;


    // Campos
    private String nombre,apellidoP,apellidoM,rfc;
    // solo para validar formato de rfc
    private LocalDate fechaNacimiento;

    public AltaProfeBeanUI(){
        guardarHelper = new AltaProfesorHelper();
        profe = new Profesor();
    }

    // Registrar
    public void alta(){
        try{
            profe.setNombre(nombre);
            profe.setApellidoP(apellidoP);
            profe.setApellidoM(apellidoM);
            profe.setRfc(rfc);

            guardarHelper.AltaProfesor(profe);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro Exitoso",
                            "Profesor Registrado"));
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error al registrar el profesor. Verifica los datos",null));
            e.printStackTrace();
        }
    }
    // Limpiar los campos
    public String limpiar() {
        try {
            nombre = apellidoP = apellidoM = rfc = null;
            fechaNacimiento = null;
            this.profe = new Profesor();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // validar formato del rfc
    public void validarRFC(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String entradaRFC = ((String) value).toUpperCase();

        // longitud
        if(entradaRFC == null || entradaRFC.length() !=13){
            throw  new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "El RFC debe tener 13 caracteres."));
        }

        // formato 4 letras - 6 fecha - 3 alfanuméricos
        String formato = "^[A-Z]{4}\\d{6}[A-Z0-9]{3}$";
        if (!entradaRFC.matches(formato)){
            throw  new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "El formato del RFC está incorrecto."));
        }

        // iniciales de nombre y apellidos
        String inicialesEsperadas = ((apellidoP != null ? apellidoP.substring(0, Math.min(2, apellidoP.length())) : "") +
                        (apellidoM != null ? apellidoM.substring(0, Math.min(1, apellidoM.length())) : "") +
                        (nombre != null ? nombre.substring(0, Math.min(1, nombre.length())) : "")).toUpperCase();
        String inicialesIntroducidas = entradaRFC.substring(0,4).toUpperCase();
        if (!entradaRFC.startsWith(inicialesEsperadas)){
            throw  new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "Las primeras letras del RFC no coinciden con nombre y apellidos."));
        }

        // fecha de nacimiento
        if(fechaNacimiento != null){
            String fechaEsperada = fechaNacimiento.format(DateTimeFormatter.ofPattern("yyMMdd"));
            String fechaIntroducida = entradaRFC.substring(4,10);
            if(!fechaIntroducida.equals(fechaEsperada)){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Registro inválido.",
                        "La fecha de nacimiento no coincide con el RFC."));
            }
        }
    }


    // Getters y Setters
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellidoP() {return apellidoP;}
    public void setApellidoP(String apellidoP) {this.apellidoP = apellidoP;}
    public String getApellidoM() {return apellidoM;}
    public void setApellidoM(String apellidoM) {this.apellidoM = apellidoM;}
    public String getRfc() {return rfc;}
    public void setRfc(String rfc) {this.rfc = rfc;}
    public LocalDate getFechaNacimiento() {return fechaNacimiento;}
    public void setFechaNacimiento(LocalDate fechaNac) {fechaNacimiento = fechaNac;}
}
