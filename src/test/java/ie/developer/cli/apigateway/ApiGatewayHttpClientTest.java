package ie.developer.cli.apigateway;

import ie.developer.cli.apigateway.ApiGatewayHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiGatewayHttpClientTest {

    @InjectMocks
    ApiGatewayHttpClient apiClient;

    @Mock
    CompletableFuture<HttpResponse<String>> mockCompletable;

    @Mock
    HttpResponse<String> mockResponse;

    @Mock
    HttpRequest mockRequest;

    @Spy
    HttpClient httpClient;


    @Test
    public void when_interrupted_exception_thrown() throws ExecutionException, InterruptedException {
        doReturn(mockCompletable)
                .when(httpClient).sendAsync(any(), any(HttpResponse.BodyHandlers.ofString().getClass()));
        doThrow(InterruptedException.class).when(mockCompletable).get();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            apiClient.call(mockRequest);
        });
    }

    @Test
    public void when_execution_exception_thrown() throws ExecutionException, InterruptedException {
        doReturn(mockCompletable)
                .when(httpClient).sendAsync(any(), any(HttpResponse.BodyHandlers.ofString().getClass()));
        doThrow(ExecutionException.class).when(mockCompletable).get();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            apiClient.call(mockRequest);
        });
    }

    @Test
    public void when_builder_creates_instance(){
        ApiGatewayHttpClient httpClient = ApiGatewayHttpClient.builder().build();
        assertNotNull(httpClient);
    }

}
