package Endpoints;

import Constants.EndpointConstants;
import Constants.MethodConstants;
import Exceptions.ApiServiceException;
import Extensions.Converter;
import Interfaces.EnigmaValidationEndpoint;
import Models.Param;
import Models.RequestParam;
import Models.Requests.ValidationRequest;
import Models.Responses.ValidationResponse;

import java.io.IOException;
import java.net.URISyntaxException;
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
        return Converter.Deserialize(body, ValidationResponse.class);
    }

    @Override
    protected void setParameters(ArrayList<RequestParam> params, ValidationRequest model) {
        params.add(new RequestParam("decode", model.getDecode(), Param.BODY));
        params.add(new RequestParam("encode", model.getEncode(), Param.BODY));
        params.add(new RequestParam("Content-Type", "application/json", Param.HEAD));
    }
}
