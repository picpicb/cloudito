package esipe.fr.cloudito_authentication.exceptions;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "esipe.fr.authentication.exceptions.AuthenticationException")
public class AuthenticationException extends Exception{
    private int code;
    public AuthenticationException (int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
