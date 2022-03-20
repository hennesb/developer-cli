package ie.developer.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KongCommandsTest {

    private static final String INPUT="X";
    private static final String JWT_SECRET="Y";
    private static final String ISSUER="ISSUER";



    @Test
    public void valid_jwt_format_produced(){
        KongCommands jwtGenCommand = new KongCommands();
        String jwt = jwtGenCommand.createJwt(JWT_SECRET,ISSUER);
        assertEquals(3, jwt.split("\\.").length);
    }

    @Test
    public void when_null_value_passed_excpetion_thrown(){
        KongCommands jwtGenCommand = new KongCommands();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jwtGenCommand.createJwt(null, ISSUER);
        });
    }

    @Test
    public void when_null_issuer_passed_default_issues_set_and_jwt_created(){
        KongCommands jwtGenCommand = new KongCommands();
        String jwt = jwtGenCommand.createJwt(JWT_SECRET,null);
        assertEquals(3, jwt.split("\\.").length);
    }
}
