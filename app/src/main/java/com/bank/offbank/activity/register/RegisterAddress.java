package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;

public class RegisterAddress extends AppCompatActivity implements Structure {
    private EditText cep, houseNumber, houseLetter;
    private TextView road, neighborhood, locality, state;
    private Button buttonNext, buttonReturn, search;
    private ClienteModel cliente;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register_cep);
        initializeUI();
        getDateCliente();
        Log.d("Collect: " ,"Cliente coletado" +cliente);



    }

    @Override
    public void initializeUI() {
        cep = findViewById(R.id.register_cep);
        houseNumber = findViewById(R.id.register_cep_houseNumber);
        houseLetter = findViewById(R.id.register_cep_house_letter);
        road = findViewById(R.id.register_cep_road);
        neighborhood = findViewById(R.id.register_cep_neighborhood);
        locality = findViewById(R.id.register_cep_locality);
        state = findViewById(R.id.register_cep_state);
        search = findViewById(R.id.register_cep_search);
        buttonNext = findViewById(R.id.register_next_1);
        buttonReturn = findViewById(R.id.returnScreen);

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
    private void getDateCliente(){
        Intent getDate = getIntent();
        this.cliente = (ClienteModel) getDate.getSerializableExtra("cliente");
    }

    public ClienteModel getCliente() {
        return cliente;
    }
}
