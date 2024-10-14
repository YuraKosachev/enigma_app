package Models.Responses;

import java.time.LocalDate;

public class MessageResponse {
    private final String message;
    private final LocalDate date;

    public MessageResponse(String message, LocalDate date){
        this.date = date;
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
