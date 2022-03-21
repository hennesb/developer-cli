package ie.developer.cli.apigateway;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KongGatewayInteractionCommandsTest {

    private static final String INPUT="X";
    private static final String JWT_SECRET="Y";
    private static final String ISSUER="ISSUER";



    @Test
    public void valid_jwt_format_produced(){
        KongGatewayInteractionCommands jwtGenCommand = new KongGatewayInteractionCommands();
        String jwt = jwtGenCommand.createJwt(JWT_SECRET,ISSUER);
        assertEquals(3, jwt.split("\\.").length);
    }

    @Test
    public void when_null_value_passed_excpetion_thrown(){
        KongGatewayInteractionCommands jwtGenCommand = new KongGatewayInteractionCommands();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jwtGenCommand.createJwt(null, ISSUER);
        });
    }

    @Test
    public void when_null_issuer_passed_default_issues_set_and_jwt_created(){
        KongGatewayInteractionCommands jwtGenCommand = new KongGatewayInteractionCommands();
        String jwt = jwtGenCommand.createJwt(JWT_SECRET,null);
        assertEquals(3, jwt.split("\\.").length);
    }
}
