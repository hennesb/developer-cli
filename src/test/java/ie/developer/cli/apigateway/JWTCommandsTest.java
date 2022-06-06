package ie.developer.cli.apigateway;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JWTCommandsTest {

    private static final String INPUT="X";
    private static final String JWT_SECRET="Y";
    private static final String ISSUER="ISSUER";
    private static final String SUCCESS = "Token Verified";
    private static final String FAILURE = "Failure";


    @Test
    public void valid_jwt_format_produced(){
        JWTCommands command = new JWTCommands();
        String jwt = command.createJwt(JWT_SECRET,ISSUER);
        assertEquals(3, jwt.split("\\.").length);
    }

    @Test
    public void when_null_value_passed_excpetion_thrown(){
        JWTCommands command = new JWTCommands();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            command.createJwt(null, ISSUER);
        });
    }

    @Test
    public void when_null_issuer_passed_default_issues_set_and_jwt_created(){
        JWTCommands command = new JWTCommands();
        String jwt = command.createJwt(JWT_SECRET,null);
        assertEquals(3, jwt.split("\\.").length);
    }

    @Test
    public void when_jwt_is_signed_with_correct_secret_success_returned(){
        JWTCommands command = new JWTCommands();
        String token = command.createJwt(JWT_SECRET,null);
        String isValid = command.verifyJwtSecret(token, JWT_SECRET);
        assertTrue(isValid.contains(SUCCESS));
    }

    @Test
    public void when_jwt_is_signed_with_incorrect_secret_error_returned(){
        JWTCommands command = new JWTCommands();
        String token = command.createJwt(JWT_SECRET,"MyIssuer");
        String isValid = command.verifyJwtSecret(token, JWT_SECRET+ "1");
        assertTrue(isValid.contains(FAILURE));
    }
}
