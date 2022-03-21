package ie.developer.cli.credential.management;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ShellComponent
public class PasswordManagementCommands {

    @ShellMethod(value="Create an encrypted value to add to an application properties file.", key="encrypt", prefix="-")
    public String encrypt(@NotBlank @NotNull String value, @NotBlank @NotNull String usingPassword) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(usingPassword.toCharArray());
        return textEncryptor.encrypt(value);

    }

    @ShellMethod(value="Decrypt a value, encryted by the BasicTextEncryptor. ", key="decrypt", prefix="-")
    public String decrypt(@NotBlank @NotNull String value, @NotBlank @NotNull String usingPassword) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(usingPassword.toCharArray());
        return textEncryptor.decrypt(value);

    }
}
