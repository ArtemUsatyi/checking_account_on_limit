package com.example.checking_account_on_limit.constance;

public class AccountConst {
    /**
     * Path Constance
     */
    private final static String BASE_URL = "/api";
    public final static String REGISTRATION_TRANSACTION = BASE_URL + "/registration_transaction";

    // устанавливать лимит - это отдельный запрос
    public final static String SETTING_BALANCE_LIMIT = BASE_URL +"/setting_balance_limit";
}
