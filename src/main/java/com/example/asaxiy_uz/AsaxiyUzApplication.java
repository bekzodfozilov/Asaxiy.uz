package com.example.asaxiy_uz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AsaxiyUzApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsaxiyUzApplication.class, args);

//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        try {
//            telegramBotsApi.registerBot(new TelegramController());
//        } catch (TelegramApiRequestException e) {
//            e.printStackTrace();
//        }

    }

}
