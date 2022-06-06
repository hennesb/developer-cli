package ie.developer.cli.apigateway;


import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class JwtRequests {

    private final URI baseUri;

    public JwtRequests(final URI baseUri){
        this.baseUri = baseUri;
    }

    public HttpRequest requestForConsumerJwtCreds(String jwtPath) throws URISyntaxException {
        URI fullPath = new URIBuilder(baseUri).setPath(jwtPath).build();
        return HttpRequest.newBuilder(fullPath).GET().build();
    }

}
