package ie.developer.cli.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JsonSecurityTest {

    private static class Car{
        public String model;

        public String getModel(){
            return model;
        }

        public void setModel(String s){
            this.model = s;
        }
    }

    @Test
    public void json_handles_invalid_json() throws JsonProcessingException {
        String request = "{\"model\": \"<script\"}";
        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(request, Car.class);
        assertEquals("<script", car.getModel());
    }
}
