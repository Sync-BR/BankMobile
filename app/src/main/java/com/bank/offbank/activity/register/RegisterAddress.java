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
import com.bank.offbank.model.AddressModel;
import com.bank.offbank.model.ClienteModel;
import com.bank.offbank.util.CepQuery;

public class RegisterAddress extends AppCompatActivity implements Structure {
    private EditText cep, houseNumber, houseLetter;
    private TextView road, neighborhood, locality, state;
    private Button buttonNext, buttonReturn, search;
    private ClienteModel client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register_cep);
        initializeUI();
        setupListeners();
        getDateCliente();
        Log.d("Collect: ", "Cliente coletado" + client);


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
        buttonNext = findViewById(R.id.register_cep_next);
        buttonReturn = findViewById(R.id.returnScreenCep);

    }

    @Override
    public void setupListeners() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cep.length() == 8) {
                    new Thread(() -> {
                        CepQuery searchCep = new CepQuery();
                        AddressModel addres = searchCep.searchZipCode(cep.getText().toString());
                        road.setText(addres.getLogradouro());
                        neighborhood.setText(addres.getBairro());
                        locality.setText(addres.getLocalidade());
                        state.setText(addres.getUf());
                    }).start();

                } else {
                    Toast.makeText(RegisterAddress.this, "Cep invalido!", Toast.LENGTH_SHORT).show();
                    cep.setError("O Cep precisar conter 8 digitos");
                }
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();
                if (checkDate()) {
                    Intent scrennLogin = new Intent(RegisterAddress.this, RegisterLogin.class);
                    scrennLogin.putExtra("cliente", new ClienteModel(client.getName(), client.getCpf(), client.getAge(), client.getEmail(), client.getTelephone(), cep.getText().toString(), Integer.parseInt(houseNumber.getText().toString()), houseLetter.getText().toString().charAt(0), client.getSex()));
                    startActivity(scrennLogin);
                } else {
                    enableButton();
                }
            }
        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    private boolean checkDate() {
        if (houseNumber.getText().length() != 2) {
            houseNumber.setError("Número invalido!");
            return false;
        }
        if (houseLetter.getText().length() != 1) {
            houseLetter.setError("Letra da casa invalida!");
            return false;
        }
        return true;
    }

    private void getDateCliente() {
        Intent getDate = getIntent();
        this.client = (ClienteModel) getDate.getSerializableExtra("cliente");
    }

}
