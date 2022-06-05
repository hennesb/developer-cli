package ie.developer.cli.apigateway;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class JwtRequestsTest {

    @Test
    public void jwt_request_not_null_from_passed_uri() throws URISyntaxException {
        URI endPoint = new URIBuilder().setPath("/jwt").setHost("kong").setScheme("https").build();
        JwtRequests request = new JwtRequests(endPoint);
        assertNotNull(request);
    }

    @Test
    public void jwt_request_as_string() throws URISyntaxException, MalformedURLException {
        URI endPoint = new URIBuilder().setPath("/jwt").setHost("kong").setScheme("https").build();
        assertEquals("https://kong/jwt", endPoint.toURL().toString());
    }
}
