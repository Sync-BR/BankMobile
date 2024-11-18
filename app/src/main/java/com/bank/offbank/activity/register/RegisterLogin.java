package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.model.ClienteModel;

public class RegisterLogin extends AppCompatActivity {
    private  ClienteModel client;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register_login);
        Intent getDate = getIntent();
       this.client = (ClienteModel) getDate.getSerializableExtra("cliente");
       Log.d("Reciver date client", "Dados retornado" +client);
    }
}
