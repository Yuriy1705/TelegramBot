package com;

import com.entity.Currencies;
import com.entity.CurrenciesModel;
import com.entity.Model;
import com.entity.Weather;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBot = new TelegramBotsApi();

        try {
            telegramBot.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendResp(message, "Hi! I am chat bot Valerka!!! A bot created by Great and Mighty Java Developer " +
                            "Yuriy Nesterenko for educational purposes!!!");
                    break;
                case "/help":
                    sendResp(message, "Can I help you?");
                    break;
                case "/setting":
                    sendResp(message, "What do you want to set up?");
                    break;
                case "курс валют":
                    CurrenciesModel currenciesModel = new CurrenciesModel();
                    try {
                        sendResp(message,Currencies.getCurrencies(currenciesModel));
                    } catch (IOException e) {
                        sendResp(message,"Hmmm, it is not currencies request....");
                    }
                    break;
                default:
                    Model model = new Model();
                    try {
                        sendResp(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendResp(message, "Ask not found\n" +
                                "=================================\n" +
                                "Здесь могла быть ваша реклама))))\n" +
                                "=================================");
                    }

                    break;

            }
        }

    }

    public String getBotUsername() {
        return "TestMetcastBot";
    }

    public String getBotToken() {
        return "643493145:AAFjTPH96BclDfJ4f0npHq7K-80_m24dBPk";
    }

    private void sendResp(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButton(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();

        keyboardButtons.add(new KeyboardButton("/help"));
        keyboardButtons.add(new KeyboardButton("/setting"));

        keyboardRows.add(keyboardButtons);
        replyKeyboardMarkup.setKeyboard(keyboardRows);


    }
}
