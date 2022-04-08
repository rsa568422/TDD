import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class Flight {

    private String reference;

    private LocalDateTime departureDate;

    private String origin;

    private String destination;

    public Flight(String reference, LocalDateTime departureDate, String origin, String destination) {
        this.reference = reference;
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
    }

}
