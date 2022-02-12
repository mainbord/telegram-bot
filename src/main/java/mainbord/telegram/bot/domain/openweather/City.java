package mainbord.telegram.bot.domain.openweather;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    String id;
    String name;
    Coordinate coord;
    String country;
    Long population;
    Long timezone;
    Long sunrise;
    Long sunset;
}
