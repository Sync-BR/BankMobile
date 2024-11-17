package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        setupListeners();
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
        buttonReturn = findViewById(R.id.returnScreenCep);

    }

    @Override
    public void setupListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();

                if(cep.length() == 8){
                    //Metado para buscar um cep e prencher os dados no form data
                } else {
                    enableButton();
                    Toast.makeText(RegisterAddress.this, "Cep invalido!", Toast.LENGTH_SHORT).show();
                    cep.setError("O Cep precisar conter 8 digitos");
                }
            }
        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Resposta", "Botão clicado");
                Intent screenReturn = new Intent(RegisterAddress.this, RegisterActivity.class);
                screenReturn.putExtra("cliente", cliente);
                startActivity(screenReturn);
            }
        });

    }

    @Override
    public void disableButton() {
        buttonNext.setEnabled(false);
        buttonReturn.setEnabled(false);

    }

    @Override
    public void enableButton() {
        buttonNext.setEnabled(true);
        buttonReturn.setEnabled(true);
    }
    private void getDateCliente(){
        Intent getDate = getIntent();
        this.cliente = (ClienteModel) getDate.getSerializableExtra("cliente");
    }

}
