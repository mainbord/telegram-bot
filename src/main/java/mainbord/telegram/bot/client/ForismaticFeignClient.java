package mainbord.telegram.bot.client;

import feign.QueryMap;
import feign.RequestLine;
import mainbord.telegram.bot.domain.forismatic.ForismaticResponse;

import java.util.Map;

public interface ForismaticFeignClient {

    @RequestLine("GET /api/1.0/")
    ForismaticResponse getRandomQuotation(@QueryMap Map<String, String> params);
}
