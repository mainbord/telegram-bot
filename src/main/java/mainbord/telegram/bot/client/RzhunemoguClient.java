package mainbord.telegram.bot.client;

import feign.Feign;
import feign.jaxb.JAXBEncoder;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguRandomRequestType;
import mainbord.telegram.bot.config.AppConfig;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Log4j2
public class RzhunemoguClient {

    private final RzhunemoguFeignClient feignClient;
    private final String rzhunemoguEndpoint = AppConfig.getProperties().getProperty("client.rzhunemogu.url");

    @SneakyThrows
    public RzhunemoguClient() {
        feign.jaxb.JAXBContextFactory jaxbFactory = new feign.jaxb.JAXBContextFactory.Builder()
//                .withProperty(Marshaller.JAXB_ENCODING, "windows-1251")
                .withMarshallerJAXBEncoding("windows-1251")
                .withMarshallerFormattedOutput(true)
                .build();
        this.feignClient = Feign.builder()
                .logger(new feign.Logger() {
                    @Override
                    protected void log(String configKey, String format, Object... args) {
                        log.info(String.format(methodTag(configKey) + format, args));
                    }
                })
                .logLevel(feign.Logger.Level.FULL)
                .encoder(new JAXBEncoder(jaxbFactory))
//                .decoder(new JAXBDecoder(jaxbFactory))
                .target(RzhunemoguFeignClient.class, rzhunemoguEndpoint);
    }

    //TODO нормально настроить декодер феин клиента, у ржунемогу по ходу ошибка в теле запроса кодировка utf-8
    // а в http заголовке windows-1251, ну и по ходу декодер смотрит на кодировку в теле
    @SneakyThrows
    public String getRandomAnekdotJoke(RzhunemoguRandomRequestType cType) {
        JAXBContext jaxbContext = JAXBContext.newInstance(RzhunemoguResponse.class);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
        Map<String, String> params = new HashMap<>();
        params.put("CType", String.valueOf(cType.getcType()));
        String response = new String(feignClient.getRandomJoke(params), "windows-1251");
        return ofNullable(((RzhunemoguResponse) jaxbMarshaller.unmarshal(new StringReader(response))))
                .orElse(RzhunemoguResponse.builder().build()).getContent();
    }
}
