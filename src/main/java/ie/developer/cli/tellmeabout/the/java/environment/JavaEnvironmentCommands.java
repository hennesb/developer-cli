package ie.developer.cli.tellmeabout.the.java.environment;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Properties;
import java.util.function.Consumer;

@ShellComponent
public class JavaEnvironmentCommands {

    private Consumer<X509Certificate> certConsumer = c -> {
        try {
            printCertDetails(c);
        } catch (CertificateEncodingException | CertificateNotYetValidException | CertificateExpiredException e) {
            throw new RuntimeException(e);
        }
    };

    @ShellMethod(value="Print java environment details", key="java-env", prefix="-")
    public void javaEnv() throws NoSuchAlgorithmException, KeyStoreException {
        printJavaSystemPropertiesToSysOut();

    }

    @ShellMethod(value="Print java default truststore details", key="java-certs", prefix="-")
    public void javaDefaultCertDetails() throws NoSuchAlgorithmException, KeyStoreException {
        printDefaulEnvCertDetails();

    }

    private void printJavaSystemPropertiesToSysOut() {
        Properties properties = System.getProperties();
        properties.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    private void printDefaulEnvCertDetails() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX");
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        Arrays.stream(acceptedIssuers).forEach(certConsumer);
    }

    private void printCertDetails(X509Certificate cert) throws CertificateEncodingException, CertificateNotYetValidException, CertificateExpiredException {
        System.out.println(String.format("Subject: %s",cert.getSubjectDN().getName()));
        System.out.println(String.format("Issuer: %s",cert.getIssuerDN().getName()));
        System.out.println(String.format("Serial Number: %d",cert.getSerialNumber()));
        System.out.println(String.format("FingerPrint : %s" ,DigestUtils.sha1Hex(cert.getEncoded())));
        System.out.println("------------------------------------------------------");
    }


}
