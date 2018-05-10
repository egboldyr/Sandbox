package api.request;

import javax.validation.constraints.NotNull;

/**
 * Created by EGBoldyr on 10.05.18.
 */

public class GeneralRequest<T> {

    @NotNull
    private T parameters;

    public T getParameters() {
        return parameters;
    }
    public void setParameters(T parameters) {
        this.parameters = parameters;
    }
}
