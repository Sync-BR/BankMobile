package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.activity.MainActivity;
import com.bank.offbank.callback.register.RegistrationStatus;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;
import com.bank.offbank.service.register.RegisterService;

public class RegisterTerms extends AppCompatActivity  implements Structure {
    private RegisterService serviceRegister;
    private ClienteModel client;
    private CheckBox acceptTerms;
    private Button button_Register, button_exit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceRegister = new RegisterService(this);
        this.client = getClientDate();
        setContentView(R.layout.activity_register_terms);
        initializeUI();
        setupListeners();

    }

    @Override
    public void initializeUI() {
        acceptTerms = findViewById(R.id.text_confirm_termo);
        button_Register = findViewById(R.id.button_register);
        button_exit = findViewById(R.id.button_register_exit);
    }

    @Override
    public void setupListeners() {
        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAcceptTerms()){
                    serviceRegister.RegisterUser(client, new RegistrationStatus() {
                        @Override
                        public void registeredSuccessfully() {
                            Toast.makeText(RegisterTerms.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent screenMain = new Intent(RegisterTerms.this, MainActivity.class);
                            startActivity(screenMain);
                            finish();
                        }

                        @Override
                        public void registrationFailed() {
                            Toast.makeText(RegisterTerms.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void serverError() {
                            Toast.makeText(RegisterTerms.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();

                        }
                    });
                    Intent screenMain = new Intent(RegisterTerms.this, MainActivity.class);
                    Toast.makeText(RegisterTerms.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(screenMain);
                } else {
                    Toast.makeText(RegisterTerms.this, "Você precisar aceitar os termos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }
    private boolean checkAcceptTerms(){
        if(acceptTerms.isChecked()){
            return true;
        }
        return false;
    }
    private ClienteModel getClientDate(){
        Log.d("Resposta", "Cliente" +client );
        Intent getDateClient = getIntent();
        return (ClienteModel) getDateClient.getSerializableExtra("cliente");
    };
}
