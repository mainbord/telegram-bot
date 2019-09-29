package mainbord.telegram.bot;

import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.service.BotService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Log4j2
public class TelegramBotApplication {

    public static void main(String[] args) {
        log.info("App Started");
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BotService());
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage());
        }
        log.info("App stopped");
    }
}
