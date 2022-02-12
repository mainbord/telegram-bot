package mainbord.telegram.bot.config;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
public class AppConfig {

    private final static Properties properties = new Properties();

    static {
        try {
            properties.load(Files.newBufferedReader(Paths.get(
                    System.getProperty("user.home") + File.separator + "BotConfig.properties")));
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
