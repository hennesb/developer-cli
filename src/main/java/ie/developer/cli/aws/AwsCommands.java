package ie.developer.cli.aws;


import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2AsyncClient;
import software.amazon.awssdk.services.ec2.Ec2AsyncClientBuilder;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;


import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ShellComponent
@Slf4j
public class AwsCommands {

    @ShellMethod(value = "List AWS instances", key = "ec2-list", prefix = "-")
    public String listInstances() throws ExecutionException, InterruptedException {
        Ec2AsyncClient ec2Client = Ec2AsyncClient.builder().region(Region.EU_WEST_1).build();
        CompletableFuture<DescribeInstancesResponse> future = ec2Client.describeInstances();
        DescribeInstancesResponse response = future.get();
        throw new UnsupportedOperationException("Implement me !");
    }
}


