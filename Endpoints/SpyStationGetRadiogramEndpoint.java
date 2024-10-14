package Endpoints;

import Adapters.LocalDateTypeAdapter;
import Constants.EndpointConstants;
import Constants.MethodConstants;
import Exceptions.ApiServiceException;
import Extensions.Converter;
import Interfaces.EnigmaRadiogramEndpoint;
import Models.Requests.MessageRequest;
import Models.Responses.MessageResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        return Converter.Deserialize(body, MessageResponse.class);
    }
}
