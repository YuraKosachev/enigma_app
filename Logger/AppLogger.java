package Logger;

import java.time.LocalDateTime;

public class AppLogger {
    //Colors
    public static final String RESET = "\033[0m";  // Text Reset

    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE

    public static void error(String message) {
        print(RED + message);
    }

    public static void info(String message) {
        print(BLUE + message);
    }

    public static void success(String message) {
        print(GREEN + message);
    }

    public static void failure(String message) {
        print(PURPLE + message);
    }

    public static void warning(String message) {
        print(YELLOW + message);
    }

    private static void print(String message) {
        System.out.println("[" + LocalDateTime.now() + "]: " + message + RESET);
    }
}
