package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;

public class RegisterLogin extends AppCompatActivity implements Structure {
    private ClienteModel client;
    private EditText username, password, repeatPassword;
    private Button buttonNext, buttonReturn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register_login);
        Intent getDate = getIntent();
        this.client = (ClienteModel) getDate.getSerializableExtra("cliente");
    }

    @Override
    public void initializeUI() {
        username.findViewById(R.id.register_login_user);
        password.findViewById(R.id.register_login_password);
        repeatPassword.findViewById(R.id.register_repeat_password);
        buttonNext.findViewById(R.id.register_login_next);
        buttonReturn.findViewById(R.id.return_login_screen);

    }

    @Override
    public void setupListeners() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificar se o login já é existente no banco de dados.

                //Lófica para verificar se os dados estão correto
                if(checkDate()){
                    Intent screenTerms = new Intent(RegisterLogin.this, null);
                    screenTerms.putExtra("cliente", client);
                    startActivity(screenTerms);
                }
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
        if (username.getText().length() <= 6) {
            username.setError("Nome do usuário deve ter mais de 6 caracteres");
            return false;
        }
        if (password.getText().length() <= 6) {
            password.setError("Senha deve ter mais de 6 caracteres");
            return false;
        }
        if(repeatPassword.getText().length() <= 6){
            if(repeatPassword.getText() != password.getText()){
                repeatPassword.setError("Senhas diferentes!");
                return false
            }
            repeatPassword.setError("Senha deve ter mais de 6 caracteres");
            return false;
        }

        return true;
    }
}
