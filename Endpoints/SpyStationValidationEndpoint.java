package Endpoints;

import Constants.EndpointConstants;
import Constants.MethodConstants;
import Exceptions.ApiServiceException;
import Exceptions.ValidationException;
import Extensions.JsonConverter;
import Interfaces.EnigmaValidationEndpoint;
import Models.Param;
import Models.RequestParam;
import Models.Requests.MessageRequest;
import Models.Requests.ValidationRequest;
import Models.Responses.MessageResponse;
import Models.Responses.ValidationResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SpyStationValidationEndpoint extends SpyDataClient<ValidationRequest, ValidationResponse>
        implements EnigmaValidationEndpoint {
    public SpyStationValidationEndpoint() {
        super(EndpointConstants.VALIDATION_ENDPOINT, MethodConstants.POST);
    }

    @Override
    public ValidationResponse validate(ValidationRequest request)
            throws ApiServiceException, IOException, InterruptedException, URISyntaxException {
        return execute(request);
    }

    @Override
    protected ValidationResponse Converter(String body) {
        JsonConverter converter = new JsonConverter(body);
        String message = converter.getValue("message");
        boolean isValid = Boolean.parseBoolean(converter.getValue("isValid"));
        return new ValidationResponse(message, isValid);
    }

    @Override
    protected void setParameters(ArrayList<RequestParam> params, ValidationRequest model) {
        params.add(new RequestParam("decode", model.getDecode(), Param.BODY));
        params.add(new RequestParam("encode", model.getEncode(), Param.BODY));
        params.add(new RequestParam("Content-Type", "application/json", Param.HEAD));
    }

    @Override
    protected boolean canHandling(HttpResponse<String> response) {
        var body = response.body();
        return response.statusCode() == 400;
    }
}
