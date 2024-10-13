package Models.Responses;

public class ValidationResponse {
    private final String message;
    private final boolean isValid;
    public ValidationResponse(String message, boolean isValid){
        this.isValid = isValid;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid(){
        return isValid;
    }
}
