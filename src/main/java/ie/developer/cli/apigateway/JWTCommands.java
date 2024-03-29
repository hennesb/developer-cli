package ie.developer.cli.apigateway;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@ShellComponent
@Slf4j
public class JWTCommands {


    @ShellMethod(value = "Generate a signed HS256 type JWT token.", key = "jwt-create", prefix = "-")
    public String createJwt(@NotBlank final String secret, @ShellOption(defaultValue = "") final String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer(issuer)
                .sign(algorithm);
    }

    @ShellMethod(value = "Generate a signed HS256 type JWT token with a base64 encoded secret", key = "jwt-create-with-b64-secret", prefix = "-")
    public String createJWTWithB64Secret(@NotBlank final String secret, @ShellOption(defaultValue = "") final String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8)));
        return JWT.create().withIssuer(issuer)
                .sign(algorithm);
    }

    @ShellMethod(value = "Verify a JWT was signed with a supplied secret", key = "jwt-verify-with-b64-secret", prefix = "-")
    public String verifyJwtWithBase64Secret(@NotBlank final String token, @NotBlank final String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8)));
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return "Token Verified : Token was signed with supplied secret";
        } catch (JWTVerificationException exception) {
            return "Failure: Token could not be verified. Secret supplied was not used to sign this token";
        }
    }

    @ShellMethod(value = "Verify a JWT was signed with a supplied secret encoded in base64", key = "jwt-verify", prefix = "-")
    public String verifyJwtSecret(@NotBlank final String token, @NotBlank final String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return "Token Verified : Token was signed with supplied secret";
        } catch (JWTVerificationException exception) {
            return "Failure: Token could not be verified. Secret supplied was not used to sign this token";
        }
    }

    @ShellMethod(value = "Print key JWT details , secret, issuer and subject", key = "jwt-print", prefix = "-")
    public String printJwt(@NotBlank final String token, @NotBlank final String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return printableJwtDetails(jwt, secret);

        } catch (JWTVerificationException exception) {
            return "Failure: Token could not be verified. Secret supplied was not used to sign this token. Cannot print details";
        }
    }

    private String printableJwtDetails(final DecodedJWT jwt, final String secret) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Secret: ").append(secret).append("\n")
                .append("Subject: ").append(jwt.getSubject()).append("\n")
                .append("nbf: ").append(jwt.getNotBefore()).append("\n")
                .append("Type: ").append(jwt.getType()).append("\n")
                .append("Issuer if in Header: ").append(jwt.getHeaderClaim("iss")).append("\n")
                .append("Issuer if in Body: ").append(jwt.getIssuer()).append("\n");
        return buffer.toString();
    }
}

