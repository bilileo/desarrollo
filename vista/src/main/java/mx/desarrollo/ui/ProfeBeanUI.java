package mx.desarrollo.ui;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarollo.entity.Profesor;
import mx.desarrollo.helper.ProfesorHelper;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Named("profeBeanUI")
@ViewScoped
public class ProfeBeanUI implements Serializable {


    private ProfesorHelper helper; // Para guardar y consultar
    private Profesor profe;        // Para registro individual
    private List<Profesor> profesores; // Para mostrar en la tabla


    // Campos para alta de profesor
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String rfc;
    private LocalDate fechaNacimiento;


    // =========================
    // CONSTRUCTOR
    // =========================
    public ProfeBeanUI() {
        helper = new ProfesorHelper();
        profe = new Profesor();
    }


    // =========================
    // POSTCONSTRUCT (carga inicial)
    // =========================
    @PostConstruct
    public void init() {
        try {
            profesores = helper.obtenerProfesoresConUA();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error al cargar profesores",
                            e.getMessage()));
            e.printStackTrace();
        }
    }


    // =========================
    // MeTODO DE ALTA
    // =========================
    public void alta() {
        try {
            validarRFC(nombre, apellidoP, apellidoM, fechaNacimiento, rfc);


            profe.setNombre(nombre);
            profe.setApellidoP(apellidoP);
            profe.setApellidoM(apellidoM);
            profe.setRfc(rfc);


            helper.AltaProfesor(profe);


            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro Exitoso",
                            "Profesor Registrado"));


            limpiar(); // reinicia el formulario


            // recargar lista
            profesores = helper.obtenerProfesoresConUA();


        } catch (ValidatorException ve) {
            FacesContext.getCurrentInstance().addMessage(null, ve.getFacesMessage());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error al registrar el profesor. Verifica los datos", e.getMessage()));
            e.printStackTrace();
        }
    }


    // =========================
    // VALIDACIÓN DEL RFC
    // =========================
    public void validarRFC(String nom, String apP, String apM, LocalDate fechaNac, String rfc) throws ValidatorException {
        if (rfc == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "El RFC no puede ser nulo."));
        }


        String entradaRFC = rfc.toUpperCase();


        if (entradaRFC.length() != 13) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "El RFC debe tener 13 caracteres."));
        }


        String formato = "^[A-Z]{4}\\d{6}[A-Z0-9]{3}$";
        if (!entradaRFC.matches(formato)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "El formato del RFC está incorrecto."));
        }


        String inicialesEsperadas =
                ((apP != null ? apP.substring(0, Math.min(2, apP.length())) : "") +
                        (apM != null ? apM.substring(0, Math.min(1, apM.length())) : "") +
                        (nom != null ? nom.substring(0, Math.min(1, nom.length())) : ""))
                        .toUpperCase();


        String inicialesIntroducidas = entradaRFC.substring(0, 4);


        if (!inicialesIntroducidas.equals(inicialesEsperadas)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Registro inválido.",
                    "Las primeras letras del RFC no coinciden con nombre y apellidos."));
        }


        if (fechaNac != null) {
            String fechaEsperada = fechaNac.format(DateTimeFormatter.ofPattern("yyMMdd"));
            String fechaIntroducida = entradaRFC.substring(4, 10);
            if (!fechaIntroducida.equals(fechaEsperada)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Registro inválido.",
                        "La fecha de nacimiento no coincide con el RFC."));
            }
        }
    }


    // =========================
    // METODO PARA LIMPIAR FORMULARIO
    // =========================
    public void limpiar() {
        nombre = apellidoP = apellidoM = rfc = null;
        fechaNacimiento = null;
        this.profe = new Profesor();
    }


    // =========================
    // GETTERS Y SETTERS
    // =========================
    public List<Profesor> getProfesores() {
        return profesores;
    }


    public Profesor getProfe() {
        return profe;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellidoP() {
        return apellidoP;
    }


    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }


    public String getApellidoM() {
        return apellidoM;
    }


    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }


    public String getRfc() {
        return rfc;
    }


    public void setRfc(String rfc) {
        this.rfc = rfc;
    }


    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }


    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
