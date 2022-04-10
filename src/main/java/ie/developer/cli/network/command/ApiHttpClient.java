package ie.developer.cli.network.command;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ApiHttpClient {

    private final HttpClient client;

    public ApiHttpClient(HttpClient client){
        this.client = client;
    }

    public ApiHttpClient(){
        this.client = HttpClient.newBuilder().build();
    }

    public String call(HttpRequest request){
        System.out.println("Calling http request");
        CompletableFuture<HttpResponse<String>> response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Past this 1111");
        return getBody(response);

    }

    private String getBody(CompletableFuture<HttpResponse<String>> response){
        System.out.println("In the getBody method");
        try {
            if (response == null){
                System.out.println("Response object is null");
            }
            System.out.println("In try method");
            if (response.get() ==null){
                System.out.println("Response get object is null");
            }
            return response.get().body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
