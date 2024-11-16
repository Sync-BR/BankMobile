package com.bank.offbank.implementation;

import com.bank.offbank.implementation.callback.SexSelectedCallBack;

public interface Structure {
    void initializeUI();
    void setupListeners();
    void disableButton();
    void enableButton();
}