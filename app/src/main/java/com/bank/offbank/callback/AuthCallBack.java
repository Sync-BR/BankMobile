package com.bank.offbank.callback;

public interface AuthCallBack {
    void onAuthSuccess(String token);
    void onAuthFailure();
    void onServerFailure();
}
