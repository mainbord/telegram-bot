package mainbord.telegram.bot.client;

import feign.QueryMap;
import feign.RequestLine;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguResponse;

import java.util.Map;

public interface RzhunemoguFeignClient {

    @RequestLine("GET /Rand.aspx")
    RzhunemoguResponse getRandomJoke(@QueryMap Map<String, String> params);

}
