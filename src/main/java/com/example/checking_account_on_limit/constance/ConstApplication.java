package com.example.checking_account_on_limit.constance;

public class ConstApplication {
    /**
     * Path constance
     */
    private final static String BASE_URL = "/api";
    public final static String REGISTRATION_TRANSACTION = BASE_URL + "/registration_transaction";
    public final static String SETTING_BALANCE_LIMIT = BASE_URL + "/setting_balance_limit";
    public final static String TEST_SETTING_CONVERSION = "/test";

    /**
     * Message constance
     */
    public final static String NON_VALID_FIELD_CLIENT = "поле клиента не валидны.";
    public final static String SET_CURRENT_LIMIT = "Текущий лимит установлен";
    public final static String USER_NOT_CHANGE_LIMIT = "Лимит уже изменен. Клиент не может изменить лимит.";
    public final static String JSON_MESSAGE_ERROR = "Возникла ошибка при получении информации.";
    public final static String STATUS_OK = "Сохранение пары валюты проиошло успешно";
}
