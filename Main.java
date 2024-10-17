import Interfaces.App;
import Logger.AppLogger;

public class Main {
    public static void main(String[] args) {
        try {
            App application = InstanceFactory.getAppInstance();
            application.Start();
        } catch (Exception ex) {
            AppLogger.error(ex.getMessage());
        }
    }
}
