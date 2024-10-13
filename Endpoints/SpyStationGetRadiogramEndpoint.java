package Endpoints;

import Constants.EndpointConstants;
import Constants.MethodConstants;
import Exceptions.ApiServiceException;
import Extensions.JsonConverter;
import Interfaces.EnigmaRadiogramEndpoint;
import Models.RequestParam;
import Models.Requests.MessageRequest;
import Models.Responses.MessageResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SpyStationGetRadiogramEndpoint extends SpyDataClient<MessageRequest, MessageResponse>
        implements EnigmaRadiogramEndpoint {

    public SpyStationGetRadiogramEndpoint() {
        super(EndpointConstants.RADIOGRAM_ENDPOINT, MethodConstants.GET);
    }

    @Override
    public MessageResponse getRadiogram() throws ApiServiceException, IOException, InterruptedException, URISyntaxException {
        return execute(new MessageRequest());
    }

    @Override
    protected MessageResponse Converter(String body) {
        JsonConverter converter = new JsonConverter(body);
        String message = converter.getValue("message");
        LocalDateTime date = LocalDateTime.parse(converter.getValue("date"));
        return new MessageResponse(message, date);
    }
}
