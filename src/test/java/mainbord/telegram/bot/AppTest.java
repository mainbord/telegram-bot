package mainbord.telegram.bot;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import mainbord.telegram.bot.config.AppConfig;

public class AppTest {

    @Test
    @SneakyThrows
    public void PropertiesTest() {
        Assertions.assertNotNull(AppConfig.getProperties().getProperty("bot_token"));
        Assertions.assertNotNull(AppConfig.getProperties().getProperty("client.open_weather.appid"));
    }
}
