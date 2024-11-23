package com.bank.offbank.callback.register;

public interface RegistrationStatus {
    void registeredSuccessfully();
    void registrationFailed();
    void serverError();
}
