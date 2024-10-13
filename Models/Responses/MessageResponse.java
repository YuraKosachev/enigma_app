package Models.Responses;

import java.sql.Date;
import java.time.LocalDateTime;

public class MessageResponse {
    private final String message;
    private final LocalDateTime date;

    public MessageResponse(String message, LocalDateTime date){
        this.date = date;
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
