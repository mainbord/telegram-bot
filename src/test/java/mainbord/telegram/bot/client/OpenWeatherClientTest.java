package mainbord.telegram.bot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import mainbord.telegram.bot.UtilTest;
import mainbord.telegram.bot.domain.openweather.OpenWeatherResponse;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

class OpenWeatherClientTest {

    @Test
    @SneakyThrows
    void getWeatherMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(new StringReader(
                UtilTest.getTestObjectFromResources("OpenWeatherResponse.json")),
                OpenWeatherResponse.class);
    }

    @Test
    @SneakyThrows
    void getWeatherClient() {
        OpenWeatherClient client = new OpenWeatherClient();
        OpenWeatherResponse response = client.getWeather(1, "Moscow");
        StringBuilder sb = new StringBuilder();
        sb.append(response.getCity().getName());
        sb.append(" ");
        sb.append("\n");
        response.getList().forEach(x -> {
            sb.append(x.getDt_txt());
            sb.append(", ");
            sb.append("temp = ");
            sb.append(x.getMain().getTemp());
            sb.append("\n");
        });
        System.out.println(sb);
    }
}