package ie.developer.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Properties;

@ShellComponent
public class JavaEnvironmentCommands {

    @ShellMethod(value="Print java environment details", key="java-env", prefix="-")
    public void javaEnv() throws NoSuchAlgorithmException, KeyStoreException {
        printJavaSystemPropertiesToSysOut();

    }

    private void printJavaSystemPropertiesToSysOut() {
        Properties properties = System.getProperties();
        properties.forEach((key, value) -> System.out.println(key + ":" + value));

    }

    private void printEnvCertDetails() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX");
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        Arrays.stream(acceptedIssuers).forEach(a -> printCertDetails(a));
    }

    private void printCertDetails(X509Certificate cert){
        System.out.println(cert.toString());
    }
}
