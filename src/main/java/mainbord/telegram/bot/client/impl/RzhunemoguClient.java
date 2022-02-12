package mainbord.telegram.bot.client.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.client.RzhunemoguFeignClient;
import mainbord.telegram.bot.config.AppConfig;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguRandomRequestType;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguResponse;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class RzhunemoguClient implements RzhunemoguFeignClient {

    private final RzhunemoguFeignClient feignClient;
    private final String rzhunemoguEndpoint = AppConfig.getProperties().getProperty("client.rzhunemogu.url");
    private final Integer connectTimeoutInMS = 7000;
    private final Integer readTimeoutInMs = 7000;

    @SneakyThrows
    public RzhunemoguClient() {
        this.feignClient = Feign.builder()
                .logger(new feign.Logger() {
                    @Override
                    protected void log(String configKey, String format, Object... args) {
                        log.info(String.format(methodTag(configKey) + format, args));
                    }
                })
                .logLevel(feign.Logger.Level.FULL)
                .decoder(new JacksonDecoder(new XmlMapper()))
                .options(new Request.Options(connectTimeoutInMS, readTimeoutInMs))
                .target(RzhunemoguFeignClient.class, rzhunemoguEndpoint);
    }

    @SneakyThrows
    public String getRandomAnekdotJoke(RzhunemoguRandomRequestType cType) {
        Map<String, String> params = new HashMap<>();
        params.put("CType", String.valueOf(cType.getcType()));
        return feignClient.getRandomJoke(params).getContent();
    }

    @Override
    public RzhunemoguResponse getRandomJoke(Map<String, String> params) {
        return RzhunemoguResponse.builder().build();
    }
}
