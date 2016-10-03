package com.byethost31.mobpro.tgsmobpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser, txtPass;
    private LoginButton logButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        txtUser = (EditText) findViewById(R.id.txtname);
        assert txtUser != null;
        txtUser.addTextChangedListener(new MyTextWatcher(txtUser));

        txtPass = (EditText) findViewById(R.id.txtpass);
        assert txtPass != null;
        txtPass.addTextChangedListener(new MyTextWatcher(txtPass));

        Button bRegister = (Button) findViewById(R.id.btnregister);
        assert bRegister != null;
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(MainActivity.this, Signup.class);
                startActivity(iRegister);
            }
        });

        Button bLogin = (Button) findViewById(R.id.btnlogin);
        assert bLogin != null;
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        Button bGplus = (Button)findViewById(R.id.btnGplus);
        assert  bGplus != null;
        bGplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGPlus = new Intent(MainActivity.this, LoginGplus.class);
                startActivity(iGPlus);
            }
        });

        Button bTwitter = (Button)findViewById(R.id.btnTwitter);
        assert bTwitter != null;
        bTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTwitter = new Intent(MainActivity.this, LoginTwitter.class);
                startActivity(iTwitter);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        logButton = (LoginButton) findViewById(R.id.loginButton);
        logButton.setReadPermissions(Arrays.asList("email"));
        logButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                toScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Anda Belum Login.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error Login.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validatePassword() {
        if(txtPass.getText().toString().trim().isEmpty()){
            txtPass.setError(getString(R.string.err_msg_pass));
            requestFocus(txtPass);
            return false;
        }else {
            return true;
        }
    }

    private boolean validateUsername() {
        if(txtUser.getText().toString().trim().isEmpty()){
            txtUser.setError(getString(R.string.err_msg_username));
            requestFocus(txtUser);
            return false;
        }else {
            return true;
        }
    }

    private void requestFocus(View view) {
        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    private class MyTextWatcher implements TextWatcher {
        private View view;
        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            switch (view.getId()){
                case R.id.txtname:
                    validateUsername();
                    break;

                case R.id.txtpass:
                    validatePassword();
                    break;
            }

        }
    }

    private void toScreen() {
        Intent intent = new Intent(this, MainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void submitForm() {
        if(!validateUsername()){
            return;
        }

        if(!validatePassword()){
            return;
        }

        String username = txtUser.getText().toString();
        String password = txtPass.getText().toString();
        if (username.equals("manz")&& password.equals("2507")) {
            Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
