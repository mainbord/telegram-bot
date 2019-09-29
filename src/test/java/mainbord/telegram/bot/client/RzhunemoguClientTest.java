package mainbord.telegram.bot.client;

import lombok.SneakyThrows;
import mainbord.telegram.bot.UtilTest;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguRandomRequestType;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

class RzhunemoguClientTest {

    private RzhunemoguClient rzhunemoguClient = new RzhunemoguClient();

    @Test
    public void getRandomAnekdotJoke() {
        String joke = rzhunemoguClient.getRandomAnekdotJoke(RzhunemoguRandomRequestType.JOKE);
        System.out.println(joke);
    }

    @Test
    @SneakyThrows
    public void jaxbTest(){
        JAXBContext jaxbContext = JAXBContext.newInstance(RzhunemoguResponse.class);
        Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
        RzhunemoguResponse response = (RzhunemoguResponse) jaxbMarshaller.unmarshal(new StringReader(UtilTest.getTestObjectFromResources("RzhunemoguResponse.xml")));
        Assertions.assertNotNull(response.getContent());
        System.out.println(response.getContent());
    }
}