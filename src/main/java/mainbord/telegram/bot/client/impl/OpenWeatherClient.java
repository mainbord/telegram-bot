package mainbord.telegram.bot.client.impl;

import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.client.OpenWeatherFeignClient;
import mainbord.telegram.bot.config.AppConfig;
import mainbord.telegram.bot.domain.openweather.OpenWeatherResponse;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenWeatherClient implements OpenWeatherFeignClient{

    final String APP_ID = AppConfig.getProperties().getProperty("client.open_weather.appid");
    final OpenWeatherFeignClient feignClient;
    final String openWeatherEndpoint = AppConfig.getProperties().getProperty("client.open_weather.url");

    final String QUERY_PARAM = "q";
    final String LAT_PARAM = "lat";
    final String LON_PARAM = "lon";
    final String FORMAT_PARAM = "mode";
    final String UNITS_PARAM = "units";
    final String DAYS_PARAM = "cnt";

    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    @SneakyThrows
    public OpenWeatherClient() {
        this.feignClient = Feign.builder()
                .logger(new feign.Logger() {
                    @Override
                    protected void log(String configKey, String format, Object... args) {
                        log.info(String.format(methodTag(configKey) + format, args));
                    }
                })
                .logLevel(feign.Logger.Level.FULL)
                .decoder(new JacksonDecoder())
                .options(new Request.Options(connectTimeoutInMS, readTimeoutInMs))
                .target(OpenWeatherFeignClient.class, openWeatherEndpoint);
    }

    public OpenWeatherResponse getWeather(int numDays, String city) {
        Map<String, String> params = new HashMap<>();
        params.put(QUERY_PARAM,city);
        params.put(FORMAT_PARAM,"json");
        params.put(UNITS_PARAM,"metric");
        params.put(DAYS_PARAM,String.valueOf(numDays));
        params.put("appid", APP_ID);
        return feignClient.getWeather(params);
    }

    @Override
    public OpenWeatherResponse getWeather(Map<String, String> params) {
        return null;
    }
}
