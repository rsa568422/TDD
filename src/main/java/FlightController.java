import java.util.HashMap;
import java.util.Map;

public class FlightController {

    private Map<String, Flight> flights;

    public FlightController() {
        this.flights = new HashMap<>();
    }

    public void addFlight(Flight flight) {
        Flight existingFlight = this.flights.get(flight.getReference());

        if (existingFlight != null) {
            throw new DuplicateFlightException();
        }

        this.flights.put(flight.getReference(), flight);
    }

    public Flight findFlightByReference(String reference) {
        Flight flight = flights.get(reference);

        if (flight == null) {
            throw new FlightNotFoundException();
        }

        return flight;
    }
}
