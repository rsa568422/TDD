import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Flight> findFlightByOrigin(String origin) {
        return this.flights
                .values()
                .stream()
                .filter(flight -> flight.getOrigin().equals(origin))
                .collect(Collectors.toList());
    }

    public List<Flight> findFlightByDepartureDate(LocalDateTime startDate, LocalDateTime endDate) {
        return this.flights
                .values()
                .stream()
                .filter(flight -> flight.getDepartureDate().isAfter(startDate) && flight.getDepartureDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public void remove(String reference) {
        this.flights.remove(reference);
    }

}
