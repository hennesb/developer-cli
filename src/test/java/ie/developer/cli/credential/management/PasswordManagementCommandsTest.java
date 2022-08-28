package ie.developer.cli.credential.management;

import ie.developer.cli.credential.management.command.PasswordManagementCommands;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;


public class PasswordManagementCommandsTest {

    private static final String INPUT="A";
    private static final String TEST_PASSWORD="B";
    private static final String WRONG_PASSWORD="C";

    @Test
    public void when_non_null_input_and_passed_encryped_value_returned(){
        PasswordManagementCommands command = new PasswordManagementCommands();
        assertNotNull(command.encrypt(INPUT,TEST_PASSWORD));
    }

    @Test
    public void when_null_input_exception_thrown(){
        PasswordManagementCommands command = new PasswordManagementCommands();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            command.encrypt(null,null );
        });
    }

    @Test
    public void when_null_input_on_decryption_exception_thrown(){
        PasswordManagementCommands command = new PasswordManagementCommands();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            command.decrypt(null,null );
        });
    }


    @Test
    public void encryt_then_decrypt_returns_correct_decryption_value(){
        PasswordManagementCommands command = new PasswordManagementCommands();
        String encrypted = command.encrypt(INPUT,TEST_PASSWORD);
        assertEquals(INPUT, command.decrypt(encrypted, TEST_PASSWORD));
    }

    @Test
    public void when_decrypted_with_wrong_key_exception_thrown(){
        PasswordManagementCommands command = new PasswordManagementCommands();
        String encrypted = command.encrypt(INPUT,TEST_PASSWORD);
        Exception exception = assertThrows(EncryptionOperationNotPossibleException.class, () -> {
            command.decrypt(encrypted,WRONG_PASSWORD );
        });
    }


    private static String getenv(String variable) {
        return ((Function<String, String>) System::getenv).apply(variable);
    }

    @Test
    public void when_system_property(){
        Function<String,String> systemProperty = s -> System.getenv(s);
    }

    private String apply(Function<String,String> func, String key){
        return func.apply(key);
    }


    @Test
    public int[] boolean_chain_test(int[] array, int targetSum) {
        List<Integer> response = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if ((array[i] + array[j]) == targetSum) {
                    if (array[i] != array[j]) {
                        response.add(array[i]);
                        response.add(array[j]);
                    }

                }
            }
        }
        if (response.size() > 0) {
            int[] solution = {response.get(0), response.get(1)};
            return solution;
        } else {
            return new int[0];
        }


    }


}
