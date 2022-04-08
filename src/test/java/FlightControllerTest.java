import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

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
        Flight flight = FlightControllerTestMother.FLIGHT1;

        //WHEN
        this.flightController.addFlight(FlightControllerTestMother.FLIGHT1);

        //THEN
        assertEquals(flight, this.flightController.findFlightByReference("1"));
    }

    @Test
    void givenEmptyFlightController_whenGetByReference_thenThrowFlightNotFoundException() {
        assertThrows(FlightNotFoundException.class, () -> this.flightController.findFlightByReference("1"));
    }

    @Test
    void givenFlightControllerWithFlight_whenSameFlightAdded_thenThrowDuplicateFlightException() {
        Flight flight = FlightControllerTestMother.FLIGHT1;
        this.flightController.addFlight(flight);

        assertThrows(DuplicateFlightException.class, () -> this.flightController.addFlight(flight));
    }

    @Test
    void givenEmptyFlightController_whenThreeFlightsAdded_thenGetByOriginReturnsTwoOfThreeFlights() {
        //GIVEN
        List<Flight> flights = Arrays.asList(
                FlightControllerTestMother.FLIGHT1,
                FlightControllerTestMother.FLIGHT2,
                FlightControllerTestMother.FLIGHT3);
        List<Flight> expected = Arrays.asList(
                FlightControllerTestMother.FLIGHT1,
                FlightControllerTestMother.FLIGHT3);

        //WHEN
        flights.forEach(this.flightController::addFlight);

        //THEN
        assertEquals(expected, this.flightController.findFlightByOrigin("Málaga"));
    }

    @Test
    void givenEmptyFlightController_whenThreeFlightsAdded_thenGetByDepartureDateReturnsTwoOfThreeFlights() {
        //GIVEN
        List<Flight> flights = Arrays.asList(
                FlightControllerTestMother.FLIGHT1,
                FlightControllerTestMother.FLIGHT2,
                FlightControllerTestMother.FLIGHT3);
        List<Flight> expected = Arrays.asList(
                FlightControllerTestMother.FLIGHT1,
                FlightControllerTestMother.FLIGHT2);
        LocalDateTime startDate = LocalDateTime.of(FlightControllerTestMother.DATE1, LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.of(FlightControllerTestMother.DATE1, LocalTime.MAX);

        //WHEN
        flights.forEach(this.flightController::addFlight);

        //THEN
        assertEquals(expected, this.flightController.findFlightByDepartureDate(startDate, endDate));
    }

    @Test
    void givenEmptyFlightController_whenTwoFlightsAddedAndOneOfThemRemoved_thenGetByReferenceThrowFlightNotFoundException() {
        List<Flight> flights = Arrays.asList(
                FlightControllerTestMother.FLIGHT1,
                FlightControllerTestMother.FLIGHT2);

        flights.forEach(this.flightController::addFlight);
        this.flightController.remove("1");

        assertThrows(FlightNotFoundException.class, () -> this.flightController.findFlightByReference("1"));
    }

}
