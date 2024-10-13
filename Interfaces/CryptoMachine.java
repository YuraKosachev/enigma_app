package Interfaces;

import java.sql.Date;
import java.time.LocalDateTime;

public interface CryptoMachine {
    String decode(String code, LocalDateTime date);
}
