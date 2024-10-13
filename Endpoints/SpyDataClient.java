package Endpoints;

import Constants.EndpointConstants;
import Constants.MethodConstants;
import Exceptions.ApiServiceException;
import Interfaces.DataClient;
import Models.Param;
import Models.RequestParam;
import Models.RequestSetting;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class SpyDataClient<TRequest, TResponse> implements DataClient<TRequest, TResponse> {
    private final List<Integer> successStatusCodes = Arrays.asList(200, 201, 202, 203);
    private final String endpoint;
    private final String method;

    public SpyDataClient(String endpoint, String method) {
        this.endpoint = endpoint;
        this.method = method;
    }

    @Override
    public TResponse execute(TRequest model) throws ApiServiceException, IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();

        RequestSetting setting = getRequestSetting(endpoint, method, model);

        URL uri = new URL(new URL(EndpointConstants.SPY_STATION_HOST), setting.getEndpoint());

        HttpRequest.BodyPublisher bodyPublisher = setting.getBody() == null || method.equals(MethodConstants.GET)
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(setting.getBody());

        HttpRequest.Builder builderRequest = HttpRequest.newBuilder()
                .uri(uri.toURI())
                .method(method, bodyPublisher);

        if (setting.getHeaders().length != 0) {
            builderRequest.headers(setting.getHeaders());
        }

        HttpRequest request = builderRequest.build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return responseHandler(response);
    }

    private RequestSetting getRequestSetting(String endpoint, String method, TRequest model) {

        ArrayList<RequestParam> requestParams = new ArrayList<>();
        setParameters(requestParams, model);

        ArrayList<String> bodyBuilder = new ArrayList<>();
        ArrayList<String> headersBuilder = new ArrayList<>();
        ArrayList<String> endpointBuilder = new ArrayList<>();

        String body = null;

        for (RequestParam param : requestParams) {

            if (param.getParam() == Param.BODY) {
                Object val = param.getValue();
                if (val instanceof String)
                    bodyBuilder.add("\"" + param.getName() + "\":\"" + param.getValue() + "\"");
                else
                    bodyBuilder.add("'" + param.getName() + "':" + param.getValue());
            }
            if (param.getParam() == Param.HEAD) {
                headersBuilder.add(param.getName());
                headersBuilder.add(param.getValue().toString());
            }
            if (param.getParam() == Param.QUERY) {
                endpointBuilder.add(param.getName() + "=" + param.getValue());
            }
        }

        if (!endpointBuilder.isEmpty()) {
            endpoint = endpoint + "?" + String.join("&", endpointBuilder);
        }

        if (!bodyBuilder.isEmpty()) {
            body = "{" + String.join(",", bodyBuilder) + "}";
        }

        return new RequestSetting(body, endpoint, headersBuilder.toArray(new String[headersBuilder.size()]));
    }

    protected void setParameters(ArrayList<RequestParam> params, TRequest model) {
    }

    protected TResponse responseHandler(HttpResponse<String> response) throws ApiServiceException {

        if (!successStatusCodes.contains(response.statusCode()) && !canHandling(response)) {
            throw new ApiServiceException("Something went wrong! status code: " + response.statusCode());
        }
        return Converter(response.body());
    }

    protected abstract <TResponse> TResponse Converter(String body);

    protected boolean canHandling(HttpResponse<String> response) {
        return false;
    }
}
