package com.bank.offbank.service.usuario;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bank.offbank.activity.MainActivity;
import com.bank.offbank.callback.AuthCallBack;
import com.bank.offbank.config.configuration;
import com.bank.offbank.model.LoginModel;
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

public class UsuriousService {
    private final Context context;
    private final Gson gson = new Gson();
    private final OkHttpClient client;

    public UsuriousService(Context context) {
        this.context = context;
        client = new OkHttpClient();
    }

    public void authenticate(LoginModel login, AuthCallBack callBack) {
        final String endpoint = configuration.url+configuration.apiLogin;
        String json = gson.toJson(login);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder().url(endpoint).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ((MainActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Request", "Erro na requisição: " + e.getMessage());
                        callBack.onServerFailure();
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                String responseBody = null;
                if (body != null) {
                    responseBody = body.string();
                }
                if (response.isSuccessful()) {
                    String finalResponseBody = responseBody;
                    ((MainActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d("Token", "Autorização: " + finalResponseBody);
                                callBack.onAuthSuccess(finalResponseBody);
                            } catch (Exception e) {
                               Log.d("Exception","Error receiver token" +e.getMessage());
                            }
                        }
                    });
                } else {
                    ((MainActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           Log.d("Log dados", " Dados incorretos");
                            callBack.onAuthFailure();
                        }
                    });
                }
            }

        });

    }
}
