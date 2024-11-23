package com.bank.offbank.service.register;

import android.content.Context;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import androidx.annotation.NonNull;

import com.bank.offbank.callback.register.RegistrationStatus;
import com.bank.offbank.config.configuration;
import com.bank.offbank.model.ClienteModel;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterService {
    private final Context context;
    private final Gson gson;
    private final OkHttpClient client;

    public RegisterService(Context context) {
        this.context = context;
        client = new OkHttpClient();
        gson = new Gson();
    }

    public void RegisterUser(ClienteModel cliente, RegistrationStatus statusRegister){
        final String endPoint = configuration.url + configuration.apiRegister;
        String json = gson.toJson(cliente);
        Log.d("Cliente response", "Json:" +json);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(endPoint)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                statusRegister.serverError();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){

                } else {
                    statusRegister.registrationFailed();
                }

            }
        });



    }
}
