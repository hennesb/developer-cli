package ie.developer.cli.credential.management.command;

import ie.developer.cli.credential.management.domain.SecureValueMessage;
import ie.developer.cli.credential.management.domain.UUIDOutputMessage;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import ie.developer.cli.credential.management.crypto.PasswordCryptoFunctions;

import java.util.UUID;

@ShellComponent
public class PasswordManagementCommands {

    @ShellMethod(value="Create an encrypted value using BasicTextEncryptor.", key="encrypt", prefix="-")
    public String encrypt(@NotBlank @NotNull String value, @NotBlank @NotNull String usingPassword) {
        return PasswordCryptoFunctions.encrypt(SecureValueMessage.builder().password(usingPassword).value(value).build());

    }

    @ShellMethod(value="Decrypt a value that was encryted using the BasicTextEncryptor. ", key="decrypt", prefix="-")
    public String decrypt(@NotBlank @NotNull String value, @NotBlank @NotNull String usingPassword) {
        return PasswordCryptoFunctions.decrypt(SecureValueMessage.builder().password(usingPassword).value(value).build());
    }

    @ShellMethod(value="Generate a random UUID and encrypt it using BasicTextEncryptor", key="uuid-gen", prefix="-")
    public String uuidGenAndEncrypt(@NotBlank @NotNull String usingPassword) {
        String uuid = UUID.randomUUID().toString();
        String encryptedValue = PasswordCryptoFunctions.encrypt(SecureValueMessage.builder().password(usingPassword).value(uuid).build());
        return UUIDOutputMessage.builder().uuid(uuid).encryptedUUID(encryptedValue).build().toString();
    }
}
