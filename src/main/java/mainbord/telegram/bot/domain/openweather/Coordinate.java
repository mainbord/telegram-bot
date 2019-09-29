package mainbord.telegram.bot.domain.openweather;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordinate {
    Long lat;
    Long lon;
}
