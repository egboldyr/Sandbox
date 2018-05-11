package rest.exeption;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public class AdministrationDataException extends RuntimeException {

    private final String errorCode;

    public AdministrationDataException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
