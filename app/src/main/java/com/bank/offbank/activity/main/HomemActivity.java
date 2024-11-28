package com.bank.offbank.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;

public class HomemActivity extends AppCompatActivity implements Structure {
    private ClienteModel client;
    private TextView welcome, coins, creditCard, cardCreditLimit;
    private Button buttonPix, buttonTef, buttonCard, buttonShopping, buttonRequestALoan;

    private ClienteModel getClient() {
        Intent getInforDataClient = getIntent();
        return (ClienteModel) getInforDataClient.getSerializableExtra("cliente");
    }

    private void genereteCustumeDate(ClienteModel client) {
        welcome.setText(client.getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        genereteCustumeDate(client);
        client = getClient();
        initializeUI();
    }

    @Override
    public void initializeUI() {
        welcome = findViewById(R.id.home_wellcome);
        coins = findViewById(R.id.home_money);
        creditCard = findViewById(R.id.coinsLive);
        cardCreditLimit = findViewById(R.id.creditcardlimit);
        buttonPix = findViewById(R.id.home_pix);
        buttonTef = findViewById(R.id.home_tef);
        buttonCard = findViewById(R.id.home_cards);
        buttonShopping = findViewById(R.id.home_shopping);
        buttonRequestALoan = findViewById(R.id.home_lean_button);
    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }
}
