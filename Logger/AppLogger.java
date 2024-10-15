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
    public static final String CYAN = "\033[0;36m";

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE


    public static void error(String message) {
        print(RED + message);
    }

    public static void info(String message) {
        print(BLUE + message);
    }

    public static void success(String message) {
        print(GREEN + message);
    }
    public static void ascii(String message) {
        print(GREEN_BACKGROUND + message);
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
