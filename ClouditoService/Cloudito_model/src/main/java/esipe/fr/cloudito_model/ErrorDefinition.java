package esipe.fr.cloudito_model;


import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "la description de l'erreur")
public class ErrorDefinition   {

    public enum ErrorTypeEnum {
        TECHNICAL("TECHNICAL"),
        FUNCTIONAL("FUNCTIONAL");
        private String value;
        ErrorTypeEnum(String value) {
            this.value = value;
        }
    }

    private ErrorTypeEnum errorType = null;

    private List<ErrorDefinitionErrors> errors = null;


    public ErrorTypeEnum getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorTypeEnum errorType) {
        this.errorType = errorType;
    }

    public ErrorDefinition errors(List<ErrorDefinitionErrors> errors) {
        this.errors = errors;
        return this;
    }

    public ErrorDefinition addErrorsItem(ErrorDefinitionErrors errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<ErrorDefinitionErrors>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    public List<ErrorDefinitionErrors> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDefinitionErrors> errors) {
        this.errors = errors;
    }


}

