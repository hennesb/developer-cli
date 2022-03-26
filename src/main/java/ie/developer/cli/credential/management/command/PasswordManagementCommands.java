package ie.developer.cli.credential.management.command;

import ie.developer.cli.credential.management.domain.SecureValueMessage;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import ie.developer.cli.credential.management.crypto.PasswordCryptoFunctions;

@ShellComponent
public class PasswordManagementCommands {

    @ShellMethod(value="Create an encrypted value to add to an application properties file.", key="encrypt", prefix="-")
    public String encrypt(@NotBlank @NotNull String value, @NotBlank @NotNull String usingPassword) {
        return PasswordCryptoFunctions.encrypt(SecureValueMessage.builder().password(usingPassword).value(value).build());

    }

    @ShellMethod(value="Decrypt a value, encryted by the BasicTextEncryptor. ", key="decrypt", prefix="-")
    public String decrypt(@NotBlank @NotNull String value, @NotBlank @NotNull String usingPassword) {
        return PasswordCryptoFunctions.decrypt(SecureValueMessage.builder().password(usingPassword).value(value).build());
    }
}
