package Interfaces;

import Exceptions.ApiServiceException;
import Models.Responses.MessageResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface EnigmaRadiogramEndpoint {
    MessageResponse getRadiogram() throws ApiServiceException, IOException, InterruptedException, URISyntaxException;
}
