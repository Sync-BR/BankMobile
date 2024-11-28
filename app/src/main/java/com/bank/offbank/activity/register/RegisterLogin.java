package com.bank.offbank.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.callback.register.VerifyUserNameCallBack;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.ClienteModel;
import com.bank.offbank.model.CoinsModel;
import com.bank.offbank.model.LoginModel;
import com.bank.offbank.service.checkdate.CheckLogin;

public class RegisterLogin extends AppCompatActivity implements Structure {
    private ClienteModel client;
    private EditText username, password, repeatPassword;
    private Button buttonNext, buttonReturn;
    private CheckLogin checkService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register_login);
        Intent getDate = getIntent();
        this.client = (ClienteModel) getDate.getSerializableExtra("cliente");
        checkService = new CheckLogin(RegisterLogin.this);
        initializeUI();
        setupListeners();
    }

    @Override
    public void initializeUI() {
        username = findViewById(R.id.register_login_user);
        password = findViewById(R.id.register_login_password);
        repeatPassword = findViewById(R.id.register_repeat_password);
        buttonNext = findViewById(R.id.register_login_next);
        buttonReturn = findViewById(R.id.return_login_screen);

    }

    @Override
    public void setupListeners() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkDate()) {
                    checkService.checkUserName(username.getText().toString(), new VerifyUserNameCallBack() {
                        @Override
                        public void found(boolean status) {
                            runOnUiThread(()->{
                                if (!status) {
                                    if (!repeatPassword.getText().toString().equals(password.getText().toString())) {
                                        repeatPassword.setError("Senhas diferentes!");
                                    } else {
                                        Intent screenPhoto = new Intent(RegisterLogin.this, RegisterPhoto.class);
                                        setLoginDetails();
                                        screenPhoto.putExtra("clients", client);
                                        startActivity(screenPhoto);
                                        finish();
                                    }
                                }
                            });
                        }

                        @Override
                        public void error() {
                            Log.d("Serviço de checar", "Erro no servidor!");
                            Toast.makeText(RegisterLogin.this, "Erro na solicitação do servidor!", Toast.LENGTH_SHORT).show();
                        }
                    });

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
        if (repeatPassword.getText().length() <= 6) {
            repeatPassword.setError("Senha deve ter mais de 6 caracteres");
            return false;
        }

        return true;
    }
    //Get login details
    private void setLoginDetails(){
        CoinsModel coins = new CoinsModel(0.0,0.0);
        LoginModel login = new LoginModel(username.getText().toString(), password.getText().toString());
        client.setLogin(login);
        client.setCoins(coins);

    }
}
