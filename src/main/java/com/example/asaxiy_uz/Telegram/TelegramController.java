package com.example.asaxiy_uz.Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramController extends TelegramLongPollingBot {
    @Override
    public  void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return "asahiy_bot";
    }

    @Override
    public String getBotToken() {
        return "5285131825:AAERpjALRgjpsVcegfq4csn04SjPRKDfd30";
    }
}
