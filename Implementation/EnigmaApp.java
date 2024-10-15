package Implementation;

import Exceptions.ApiServiceException;
import Interfaces.CryptoMachine;
import Interfaces.EnigmaRadiogramEndpoint;
import Interfaces.EnigmaValidationEndpoint;
import Logger.AppLogger;
import Models.Requests.ValidationRequest;
import Models.Responses.MessageResponse;
import Models.Responses.ValidationResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class EnigmaApp implements Interfaces.App {

    private final CryptoMachine cryptoMachine;
    private final EnigmaRadiogramEndpoint spyStationRadiogramEndpoint;
    private final EnigmaValidationEndpoint spyStationValidationEndpoint;
    private final int repeatAfter = 10000;

    public EnigmaApp(CryptoMachine cryptoMachine,
                     EnigmaRadiogramEndpoint spyStationRadiogramEndpoint,
                     EnigmaValidationEndpoint spyStationValidationEndpoint) {
        this.cryptoMachine = cryptoMachine;
        this.spyStationRadiogramEndpoint = spyStationRadiogramEndpoint;
        this.spyStationValidationEndpoint = spyStationValidationEndpoint;
    }

    @Override
    public void Start() throws InterruptedException, ApiServiceException, IOException, URISyntaxException {
        AppLogger.info("Application loop start");
        while (true) {
            AppLogger.info("Send request to spy station");
            MessageResponse radiogram = spyStationRadiogramEndpoint.getRadiogram();

            AppLogger.info("Radiogram message - " + radiogram.getMessage());

            AppLogger.info("Decrypting proccess...");
            String decrypt = cryptoMachine.decode(radiogram.getMessage(), radiogram.getDate());

            AppLogger.info("Send decrypt result to validation");
            ValidationRequest request = new ValidationRequest(radiogram.getMessage(), decrypt);
            ValidationResponse validation = spyStationValidationEndpoint.validate(request);

            if (validation.isValid()) {
                AppLogger.success(validation.getMessage());
                break;
            }

            AppLogger.failure(validation.getMessage());
            AppLogger.info("The request will be repeated in " + repeatAfter + " milliseconds");
            Thread.sleep(repeatAfter);
        }
    }
}
