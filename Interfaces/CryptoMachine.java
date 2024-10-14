package Interfaces;

import java.time.LocalDate;

public interface CryptoMachine {
    String decode(String code, LocalDate date);
}
