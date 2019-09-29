package mainbord.telegram.bot.service;

import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.client.OpenWeatherClient;
import mainbord.telegram.bot.client.RzhunemoguClient;
import mainbord.telegram.bot.config.AppConfig;
import mainbord.telegram.bot.domain.openweather.OpenWeatherResponse;
import mainbord.telegram.bot.domain.rzhunemogu.RzhunemoguRandomRequestType;
import org.apache.logging.log4j.util.Strings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j2
public class BotService extends TelegramLongPollingBot {

    private final static String BOT_TOKEN = AppConfig.getProperties().getProperty("bot_token");

    private final RzhunemoguClient rzhunemoguClient = new RzhunemoguClient();
    private final OpenWeatherClient openWeatherClient = new OpenWeatherClient();

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    private synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        if (Strings.isNotEmpty(s)) {
            if (s.toLowerCase().contains("анекдот")) {
                s = getRandomAnekdot();
            }
            if (s.toLowerCase().contains("погода")) {
                s = getWeather();
            }
        }
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "MainbordBot";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private String getRandomAnekdot(){
        return rzhunemoguClient.getRandomAnekdotJoke(RzhunemoguRandomRequestType.JOKE);
    }

    private String getWeather(){
        OpenWeatherResponse response = openWeatherClient.getWeather(1, "Moscow");
        StringBuilder sb = new StringBuilder();
        sb.append(response.getCity().getName());
        sb.append(" ");
        sb.append("\n");
        response.getList().forEach(x -> {
            sb.append(x.getDt_txt());
            sb.append(", ");
            sb.append("temp = ");
            sb.append(x.getMain().getTemp());
            sb.append("\n");
        });
        return sb.toString();
    }
}
