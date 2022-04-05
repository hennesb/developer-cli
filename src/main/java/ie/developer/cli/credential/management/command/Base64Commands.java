package ie.developer.cli.credential.management.command;

import ie.developer.cli.credential.management.crypto.PasswordCryptoFunctions;
import ie.developer.cli.credential.management.domain.SecureValueMessage;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ShellComponent
public class Base64Commands {


    @ShellMethod(value="Base64 encode a value", key="base64-encode", prefix="-")
    public String encrypt(@NotBlank @NotNull String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}
