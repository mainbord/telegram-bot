package mainbord.telegram.bot.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import mainbord.telegram.bot.UtilTest;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguRandomRequestType;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

class RzhunemoguClientTest {

    private final RzhunemoguClient rzhunemoguClient = new RzhunemoguClient();

    @Test
    public void getRandomAnekdotJoke() {
        String joke = rzhunemoguClient.getRandomAnekdotJoke(RzhunemoguRandomRequestType.JOKE);
        System.out.println(joke);
    }

    @Test
    @SneakyThrows
    public void jaxbTest() {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(xmlModule);

        RzhunemoguResponse response = objectMapper.reader().readValue(
                new StringReader(UtilTest.getTestObjectFromResources("RzhunemoguResponse.xml")),
                RzhunemoguResponse.class);
        Assertions.assertNotNull(response.getContent());
        System.out.println(response.getContent());
    }
}