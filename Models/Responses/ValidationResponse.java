package Models.Responses;

public class ValidationResponse {
    private final String message;
    private final boolean isValid;
    private final String ascii;
    public ValidationResponse(String message, boolean isValid, String ascii){
        this.isValid = isValid;
        this.message = message;
        this.ascii = ascii;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid(){
        return isValid;
    }
    public String getAscii(){
        return ascii;
    }
}
