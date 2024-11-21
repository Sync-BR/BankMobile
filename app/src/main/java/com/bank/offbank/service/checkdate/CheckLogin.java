package com.bank.offbank.service.checkdate;

import static com.bank.offbank.config.configuration.apiCheckUsername;
import static com.bank.offbank.config.configuration.url;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bank.offbank.activity.register.RegisterLogin;
import com.bank.offbank.callback.register.VerifyUserNameCallBack;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CheckLogin {
    private Gson gson;
    private Context context;
    private final OkHttpClient client;

    public CheckLogin(Context context) {
        this.context = context;
        this.client = new OkHttpClient();
        gson = new Gson();

    }

    public void checkUserName(String username, VerifyUserNameCallBack verify) {
        final String endPoint = url + apiCheckUsername + username;
        Request request = new Request.Builder()
                .url(endPoint)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    verify.error();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    boolean isAvaliable = Boolean.parseBoolean(response.body().string());
                    verify.found(isAvaliable);
                } else {
                    verify.error();
                }
            }
        });

    }
}
