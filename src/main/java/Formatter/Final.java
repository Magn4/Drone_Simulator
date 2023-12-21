package Formatter;

import java.io.IOException;

import Formatter.Formatte.Car;

import static org.junit.Assert.*;

public class DemoTest {

    @Test
    public void testMain() throws IOException {
        String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        // 1. Convert JSON to Java Object
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);

        // Print the cars
        for (Car car : cars) {
            System.out.println(car);
        }

        // Assert that the cars are printed
        assertEquals("Black", cars[0].getColor());
        assertEquals("BMW", cars[0].getType());
        assertEquals("Red", cars[1].getColor());
        assertEquals("FIAT", cars[1].getType());
    }
}
