package ie.developer.cli.network.command;

import lombok.extern.java.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@ShellComponent
@Log
public class NetworkCommands {

    private static final String CONNECTING_TO = "Connecting to host %s on port %d with timeout %d";
    private static final String CONNECT_SUCCESS = "Connection successful to host %s on port %d";
    private static final String CONNECT_FAILURE = "Connection failed to host %s on port %d";


    @ShellMethod(value="Create a Socket connection to host", key="tcp-connect", prefix="-")
    public String tcpConnect(String host, int port, @Max(60000) @Min(1) @ShellOption(defaultValue="3000") int timeout) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            return String.format(CONNECT_SUCCESS, host, port);
        } catch (IOException e) {
            return String.format(CONNECT_FAILURE, host, port);
        }
    }
}
