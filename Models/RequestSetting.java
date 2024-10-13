package Models;

public class RequestSetting {
    private final String body;
    private final String endpoint;
    private final String[] headers;

    public RequestSetting(String body, String endpoint, String[] headers){
        this.body = body;
        this.endpoint = endpoint;
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String[] getHeaders() {
        return headers;
    }
}
