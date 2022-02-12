package mainbord.telegram.bot;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mainbord.telegram.bot.service.BotService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Log4j2
public class TelegramBotApplication {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("App Started");

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new BotService());
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage());
        }
        log.info("App stopped");
    }
}
