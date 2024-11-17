package com.bank.offbank.util;

import android.util.Log;

import com.bank.offbank.activity.MainActivity;
import com.bank.offbank.model.AddressModel;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CepQuery {
    private static boolean zipFound = false;
    private static String url = "https://viacep.com.br/ws/";

    public AddressModel searchZipCode(String cep) {
        String endPoint = url + cep + "/json/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(endPoint)
                .build();
        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            if (response.isSuccessful() || response.body() != null) {
                String jsonResponse = response.body().string();
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, AddressModel.class);
            } else {
                Log.d("Response", "Erro de resposta no servidor");
                return null;
            }


        } catch (IOException e) {
            Log.d("Warring IOException", "Erro " + e.getMessage());
            return null;

        }
    }

    public static void main(String[] args) {
        CepQuery c = new CepQuery();
        AddressModel adress = c.searchZipCode("40484550");
        System.out.println(adress);
    }
}
