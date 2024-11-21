package com.bank.offbank.callback.register;

public interface VerifyUserNameCallBack {
    void found(boolean status);
    void error();
}
