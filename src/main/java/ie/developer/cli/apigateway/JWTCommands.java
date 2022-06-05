package ie.developer.cli.apigateway;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.NotBlank;


@ShellComponent
@Slf4j
public class JWTCommands {


    @ShellMethod(value = "Generate a JWT token.", key = "jwt-gen", prefix = "-")
    public String createJwt(@NotBlank String secret, @ShellOption(defaultValue = "") String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer(issuer)
                .sign(algorithm);
    }

    @ShellMethod(value = "Verify JWT secret", key = "jwt-verify", prefix = "-")
    public String verifyJwtSecret(@NotBlank String token, @NotBlank String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return "Token Verified : Token was signed with supplied secret";
        } catch (JWTVerificationException exception){
            return "Failure: Token could not be verified. Secret supplied was not used to sign this token";
        }

    }
}

