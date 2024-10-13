package Interfaces;

import Exceptions.ApiServiceException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DataClient<TRequest,TResponse> {
    TResponse execute(TRequest model) throws ApiServiceException, IOException, InterruptedException, URISyntaxException;
}

