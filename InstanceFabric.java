import Endpoints.SpyStationGetRadiogramEndpoint;
import Endpoints.SpyStationValidationEndpoint;
import Implementation.EnigmaApp;
import Interfaces.App;
import Interfaces.CryptoMachine;
import CryptoMashine.EnigmaMachine;
import Interfaces.EnigmaRadiogramEndpoint;
import Interfaces.EnigmaValidationEndpoint;

public class InstanceFabric {

    public static CryptoMachine getCryptoMachineInstance() {
        return new EnigmaMachine();
    }

    //Endpoints
    public static EnigmaValidationEndpoint getEnigmaValidationInstance() {
        return new SpyStationValidationEndpoint();
    }

    public static EnigmaRadiogramEndpoint getEnigmaRadiogramInstance() {
        return new SpyStationGetRadiogramEndpoint();
    }

    public static App getAppInstance() {
        return new EnigmaApp(getCryptoMachineInstance(), getEnigmaRadiogramInstance(), getEnigmaValidationInstance());
    }
}

