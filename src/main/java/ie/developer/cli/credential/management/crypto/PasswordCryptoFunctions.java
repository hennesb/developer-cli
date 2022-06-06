package ie.developer.cli.credential.management.crypto;

import ie.developer.cli.credential.management.domain.SecureValueMessage;
import org.jasypt.util.text.BasicTextEncryptor;


public class PasswordCryptoFunctions {

    private PasswordCryptoFunctions(){

    }

    public static String encrypt(SecureValueMessage message) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(message.getPassword().toCharArray());
        return textEncryptor.encrypt(message.getValue());
    }

    public static String decrypt(SecureValueMessage message) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(message.getPassword().toCharArray());
        return textEncryptor.decrypt(message.getValue());

    }
}
