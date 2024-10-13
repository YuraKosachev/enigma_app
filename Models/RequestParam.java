package Models;

public class RequestParam {
    private final String name;
    //    public final String value;
    private final Object value;
    private final Param param;

    public RequestParam(String name, Object value, Param param) {
        this.name = name;
        this.value = value;
        this.param = param;
    }

    public Param getParam() {
        return param;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
