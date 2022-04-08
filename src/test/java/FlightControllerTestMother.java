import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FlightControllerTestMother {

    public final static LocalDate DATE1 = LocalDate.of(2022, 1, 1);
    public final static LocalDate DATE2 = LocalDate.of(2022, 1, 2);

    public final static Flight FLIGHT1 =
            new Flight("1", LocalDateTime.of(DATE1, LocalTime.of(10, 30)), "Málaga", "Bilbao");

    public final static Flight FLIGHT2 =
            new Flight("2", LocalDateTime.of(DATE1, LocalTime.of(11, 15)), "Madrid", "Bilbao");

    public final static Flight FLIGHT3 =
            new Flight("3", LocalDateTime.of(DATE2, LocalTime.of(9, 0)), "Málaga", "Santander");

}
