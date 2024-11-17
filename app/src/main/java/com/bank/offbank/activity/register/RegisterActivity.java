package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.activity.MainActivity;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;

public class RegisterActivity extends AppCompatActivity implements Structure {
    private EditText name, cpf, age, email, telephone;
    private Button buttonNext, buttonReturn;
    private Spinner sex;
    private ClienteModel dateCliente;

    private boolean returnDate() {
        Intent getDate = getIntent();
        this.dateCliente = (ClienteModel) getDate.getSerializableExtra("cliente");
        if (dateCliente != null) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (returnDate()) {
            System.out.println("Age: " +age.getText());
            name.setText(dateCliente.getName());
            cpf.setText(dateCliente.getCpf());
            email.setText(dateCliente.getEmail());
            age.setText(String.valueOf(dateCliente.getAge()));
            Log.d("Log cliente", "Valores obtidos: " +dateCliente);
            telephone.setText(dateCliente.getTelephone());
            if (dateCliente.getSex().equals("Masculino")) {
                sex.setSelection(1);
            } else if(dateCliente.getSex().equals("Feminino")){
                sex.setSelection(2);
            } else {}
            sex.setSelection(0);

        }
        enableButton();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeUI();
        setupListeners();
        spinnerAdapter();
        onResume();
    }

    @Override
    public void initializeUI() {
        name = findViewById(R.id.register_name);
        cpf = findViewById(R.id.register_cpf);
        age = findViewById(R.id.register_age);
        email = findViewById(R.id.register_email);
        telephone = findViewById(R.id.register_telephone);
        sex = findViewById(R.id.register_sexo);
        buttonNext = findViewById(R.id.register_next_1);
        buttonReturn = findViewById(R.id.returnScreen);
    }


    @Override
    public void setupListeners() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFields()) {
                    disableButton();
                    if (sex.getSelectedItem().toString() != "Selecione") {
                        Intent nextScrenn = new Intent(RegisterActivity.this, RegisterAddress.class);
                        nextScrenn.putExtra("cliente", new ClienteModel(name.getText().toString(), cpf.getText().toString(), Integer.parseInt(age.getText().toString()), email.getText().toString(), telephone.getText().toString(), sex.getSelectedItem().toString()));
                        startActivity(nextScrenn);
                    } else {
                        enableButton();
                        Toast.makeText(RegisterActivity.this, "Selecione um sexo", Toast.LENGTH_SHORT).show();
                    }
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
        sex.setAdapter(adapter);
    }

    private String[] getSex() {
        return new String[]{"Selecione", "Masculino", "Feminino"};
    }

    private boolean checkFields() {

        if (name.length() <= 2) {
            name.setError("Nome invalido");
            return false;
        }
        if (cpf.length() <= 7) {
            cpf.setError("Cpf deve possuir 8 numeros");
            return false;
        }
        if (Integer.parseInt(age.getText().toString()) <= 17) {
            age.setText("O Usuário deve possuir 18 anos ou mais");
            return false;
        }
        String emailCoppy = email.getText().toString();
        if (email.length() <= 15) {
            if (!emailCoppy.contains("@")) {
                email.setError("Email invalido");
                return false;
            }
            email.setError("Email deve possuir 15 caracteres");
            return false;
        }
        if (telephone.length() <= 7) {
            telephone.setError("Telefone invalido");
        }
        return true;
    }

}



