package ie.developer.cli.apigateway;

import lombok.Builder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Builder
public class ApiGatewayHttpClient {

    private final HttpClient client;

    public ApiGatewayHttpClient(HttpClient client){
        this.client = client;
    }

    public ApiGatewayHttpClient(){
        this.client = HttpClient.newBuilder().build();
    }

    public String call(HttpRequest request){
        CompletableFuture<HttpResponse<String>> response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return getBody(response);

    }

    private String getBody(CompletableFuture<HttpResponse<String>> response){
        try {
            return response.get().body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
