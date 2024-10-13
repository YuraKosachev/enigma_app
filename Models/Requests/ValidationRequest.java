package Models.Requests;

public class ValidationRequest {
    private final String encode;
    private final String decode;
    public ValidationRequest(String encode, String decode){
        this.decode = decode;
        this.encode = encode;
    }

    public String getDecode() {
        return decode;
    }

    public String getEncode() {
        return encode;
    }
}
