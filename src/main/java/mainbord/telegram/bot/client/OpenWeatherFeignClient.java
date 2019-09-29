package mainbord.telegram.bot.client;

import feign.QueryMap;
import feign.RequestLine;
import mainbord.telegram.bot.domain.openweather.OpenWeatherResponse;

import java.util.Map;

public interface OpenWeatherFeignClient {

    @RequestLine("GET /data/2.5/forecast")
    OpenWeatherResponse getWeather(@QueryMap Map<String, String> params);

}
