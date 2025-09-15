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
import mx.desarrollo.helper.ConsultaUnidadAprendizajeHelper;

import java.io.Serializable;
import java.util.List;

@Named("consultaUABeanUI")
@SessionScoped
public class ConsultaUABeanUI implements Serializable {
    private final ConsultaUnidadAprendizajeHelper consultaHelper;
    private List<Unidadaprendizaje> listaUA;

    public ConsultaUABeanUI() {
        consultaHelper = new ConsultaUnidadAprendizajeHelper();
        cargarUA();
    }

    private void cargarUA() {
        listaUA = consultaHelper.consultarTodasUA();
    }

    public List<Unidadaprendizaje> getListaUA() {
        return listaUA;
    }

}
