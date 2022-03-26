package ie.developer.cli.network;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@ShellComponent
public class NetworkCommands {

    private static final String CONNECTING_TO = "Connecting to host %s on port %d with timeout %d";
    private static final String CONNECT_SUCCESS = "Connection successful to host %s on port %d";
    private static final String CONNECT_FAILURE = "Connection failed to host %s on port %d";


    @ShellMethod(value="Create a Socket connection to host", key="tcp-connect", prefix="-")
    public void tcpConnect(String host, int port, @Max(60000) @Min(1) @ShellOption(defaultValue="3000") int timeout) throws IOException {
        System.out.println(String.format(CONNECTING_TO, host, port, timeout));
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            System.out.println(String.format(CONNECT_SUCCESS, host, port));
        } catch (IOException e) {
            System.out.println(String.format(CONNECT_FAILURE, host, port));
        }
    }
}
