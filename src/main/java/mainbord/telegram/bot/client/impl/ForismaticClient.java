package mainbord.telegram.bot.client.impl;

import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.client.ForismaticFeignClient;
import mainbord.telegram.bot.client.OpenWeatherFeignClient;
import mainbord.telegram.bot.client.RzhunemoguFeignClient;
import mainbord.telegram.bot.config.AppConfig;
import mainbord.telegram.bot.domain.forismatic.ForismaticRequestFormat;
import mainbord.telegram.bot.domain.forismatic.ForismaticResponse;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ForismaticClient implements ForismaticFeignClient {

    private final ForismaticFeignClient feignClient;
    private final String forismaticEndpoint = AppConfig.getProperties().getProperty("client.forismatic.url");
    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    private final String METHOD_NAME = "method";
    private final String RESPONSE_FORMAT = "format";
    private final String RESPONSE_LANGUAGE = "lang";

    public ForismaticClient() {
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
                .target(ForismaticFeignClient.class, forismaticEndpoint);
    }

    public ForismaticResponse getRandomQuotation() {
        Map<String, String> params = new HashMap<>();
        params.put(METHOD_NAME, "getQuote");
        params.put("key", "999999");
        params.put(RESPONSE_FORMAT, ForismaticRequestFormat.json.toString());
        params.put(RESPONSE_LANGUAGE, "ru");
        return feignClient.getRandomQuotation(params);
    }

    @Override
    public ForismaticResponse getRandomQuotation(Map<String, String> params) {
        return ForismaticResponse.builder().build();
    }
}
