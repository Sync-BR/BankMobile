package com.bank.offbank.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bank.offbank.R;
import com.bank.offbank.activity.register.RegisterActivity;
import com.bank.offbank.callback.AuthCallBack;
import com.bank.offbank.implementation.Structure;
import com.bank.offbank.model.LoginModel;
import com.bank.offbank.service.usuario.UsuriousService;


public class MainActivity extends AppCompatActivity implements Structure {
    private UsuriousService userService;
    private Button register, login;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
        setupListeners();
    }

    @Override
    public void initializeUI() {
        userService = new UsuriousService(this);
        username = findViewById(R.id.login_usuario);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_Button_Conectar);
        register = findViewById(R.id.login_Button_Cadastrar);
    }

    @Override
    public void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();
                LoginModel userLogin = new LoginModel(username.getText().toString(), password.getText().toString());
                userService.authenticate(userLogin, new AuthCallBack() {
                    @Override
                    public void onAuthSuccess(String token) {
                        Toast.makeText(MainActivity.this, "Usuário autenticado com sucesso!", Toast.LENGTH_SHORT).show();
                        Log.d("Token: ", " do usuario " + token);
                    }

                    @Override
                    public void onAuthFailure() {
                        enableButton();
                        Toast.makeText(MainActivity.this, "Login ou senha incorretar! ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onServerFailure() {
                        enableButton();
                        Toast.makeText(MainActivity.this, "Erro no servidor! ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();
                Intent registerScrenn = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerScrenn);
                finish();
            }
        });
    }

    @Override
    public void disableButton() {
        register.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void enableButton() {
        register.setEnabled(true);
        login.setEnabled(true);
    }
}