package mainbord.telegram.bot.client.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForismaticClientTest {

    @Test
    void getRandomQuotation() {
        String response = new ForismaticClient().getRandomQuotation().getQuoteText();
        System.out.println(response);
        assertNotNull(response);
    }
}