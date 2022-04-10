package ie.developer.cli.network;

import ie.developer.cli.network.command.ApiHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApiHttpClientTest {

    @InjectMocks
    ApiHttpClient apiClient;

    @Mock
    CompletableFuture<HttpResponse<String>> mockCompletable;

    @Mock
    HttpResponse<String> mockResponse;

    @Mock
    HttpRequest mockRequest;

    @Spy
    HttpClient httpClient;


    @Test
    public void test_interrupted_thrown() throws ExecutionException, InterruptedException {
        doReturn(mockCompletable)
                .when(httpClient).sendAsync(any(), any(HttpResponse.BodyHandlers.ofString().getClass()));
        doThrow(InterruptedException.class).when(mockCompletable).get();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            apiClient.call(mockRequest);
        });
    }

    @Test
    public void test_execution_exception_thrown() throws ExecutionException, InterruptedException {
        doReturn(mockCompletable)
                .when(httpClient).sendAsync(any(), any(HttpResponse.BodyHandlers.ofString().getClass()));
        doThrow(ExecutionException.class).when(mockCompletable).get();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            apiClient.call(mockRequest);
        });
    }

}
