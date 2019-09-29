package mainbord.telegram.bot.client;

import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface RzhunemoguFeignClient {

    @RequestLine("GET /Rand.aspx")
    byte[] getRandomJoke(@QueryMap Map<String, String> params);

}
