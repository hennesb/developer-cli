package ie.developer.cli.apigateway;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.NotBlank;


@ShellComponent
    public class KongGatewayInteractionCommands {


        @ShellMethod(value="Generate a JWT token.", key="jwt-gen", prefix="-")
        public String createJwt(@NotBlank String secret, @ShellOption(defaultValue="") String issuer) {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(issuer)
                    .sign(algorithm);
        }
    }

