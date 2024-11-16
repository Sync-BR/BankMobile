package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.implementation.callback.SexSelectedCallBack;
import com.bank.offbank.model.ClienteModel;

import okhttp3.Callback;

public class RegisterActivity extends AppCompatActivity implements Structure {
    private EditText name, cpf, age, email, telephone;
    private Button buttonNext, buttonReturn;
    private Spinner sexo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeUI();
        setupListeners();
        spinnerAdapter();
    }

    @Override
    public void initializeUI() {
        name = findViewById(R.id.register_name);
        cpf = findViewById(R.id.register_cpf);
        age = findViewById(R.id.register_age);
        email = findViewById(R.id.register_email);
        telephone = findViewById(R.id.register_telephone);
        sexo = findViewById(R.id.register_sexo);
        buttonNext = findViewById(R.id.register_next_1);
        buttonReturn = findViewById(R.id.returnScreen);
    }


    @Override
    public void setupListeners() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Assign values
                if (sexo.getSelectedItem().toString() != "Selecione") {
                    int ageConvert = Integer.parseInt(age.getText().toString());
                    Log.d("Spinner log", "valor obtido" + sexo.getSelectedItem().toString());
                    Intent nextScrenn = new Intent(RegisterActivity.this, RegisterAddress.class);
                    nextScrenn.putExtra("cliente", new ClienteModel(name.getText().toString(), cpf.getText().toString(), ageConvert, email.getText().toString(), telephone.getText().toString(), sexo.getSelectedItem().toString()));
                } else {
                    Toast.makeText(RegisterActivity.this, "Selecione um sexo", Toast.LENGTH_SHORT).show();

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
        buttonReturn.setEnabled(true);
        buttonNext.setEnabled(true);
    }

    private void spinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getSex()
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexo.setAdapter(adapter);
    }

    private String[] getSex() {
        return new String[]{"Selecione", "Masculino", "Feminino"};
    }


}



