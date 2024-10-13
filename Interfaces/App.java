package Interfaces;

import Exceptions.ApiServiceException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface App {
    void Start() throws InterruptedException, ApiServiceException, IOException, URISyntaxException;
}
