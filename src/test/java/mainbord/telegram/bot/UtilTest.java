package mainbord.telegram.bot;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.stream.Collectors;

public class UtilTest {

    @SneakyThrows
    public static String getTestObjectFromResources(String fileName) {
        ClassLoader classLoader = UtilTest.class.getClassLoader();
        URL resource = classLoader.getResource("tests/" + fileName);
        return new BufferedReader(new FileReader(resource.getFile())).lines().collect(Collectors.joining());
    }

}
