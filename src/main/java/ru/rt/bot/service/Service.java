package ru.rt.bot.service;

import lombok.extern.log4j.Log4j2;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.rt.bot.client.BotEipClient;

@Log4j2
public class Service {

    public static void main(String[] args) {
        log.info("App Started");
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BotEipClient());
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage());
        }
        log.info("App stopped");
    }
}
