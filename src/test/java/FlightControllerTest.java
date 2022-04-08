import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/* TODO::   Controlador de vuelo:
            - Buscar vuelos por origen
            - Buscar vuelos entre dos fechas
            - Eliminar vuelos                   */

public class FlightControllerTest {

    private FlightController flightController;

    @BeforeEach
    void setUp() {
        this.flightController = new FlightController();
    }

    @Test
    void givenEmptyFlightController_whenFlightAdded_thenGetByReferenceReturnsFlight() {
        //GIVEN
        LocalDate date = LocalDate.of(2022, 1, 1);
        LocalTime time = LocalTime.of(10, 30);
        Flight flight = new Flight("1", LocalDateTime.of(date, time), "Madrid", "Barcelona");

        //WHEN
        this.flightController.addFlight(flight);

        //THEN
        assertEquals(flight, this.flightController.findFlightByReference("1"));
    }

    @Test
    void givenEmptyFlightController_whenGetByReference_thenThrowFlightNotFoundException() {
        assertThrows(FlightNotFoundException.class, () -> this.flightController.findFlightByReference("1"));
    }

    @Test
    void givenFlightControllerWithFlight_whenSameFlightAdded_thenThrowDuplicateFlightException() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        LocalTime time = LocalTime.of(10, 30);
        Flight flight = new Flight("1", LocalDateTime.of(date, time), "Madrid", "Barcelona");
        this.flightController.addFlight(flight);

        assertThrows(DuplicateFlightException.class, () -> this.flightController.addFlight(flight));
    }
}
