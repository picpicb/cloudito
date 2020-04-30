package esipe.fr.Cloudito_recognition.exceptions;

public class ApiException extends Exception{
    private int code;
    public ApiException(int code, String msg) {
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