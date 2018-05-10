package api.response;

/**
 * Created by EGBoldyr on 10.05.18.
 */

public class GeneralResponse<T> {

    private String code;

    private T payload;

    public GeneralResponse(String code, T payload) {
        this.code = code;
        this.payload = payload;
    }

    public String getCode() {
        return code;
    }
    public T getPayload() {
        return payload;
    }

}
