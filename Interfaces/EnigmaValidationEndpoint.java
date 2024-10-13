package Interfaces;

import Exceptions.ApiServiceException;
import Models.Requests.ValidationRequest;
import Models.Responses.ValidationResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface EnigmaValidationEndpoint {
    ValidationResponse validate(ValidationRequest request) throws ApiServiceException, IOException, InterruptedException, URISyntaxException;
}
