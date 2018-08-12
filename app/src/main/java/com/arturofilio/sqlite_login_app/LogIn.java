package com.arturofilio.sqlite_login_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arturofilio.sqlite_login_app.DatabaseHelper.DatabaseHelper;
import com.arturofilio.sqlite_login_app.models.Account;

public class LogIn extends AppCompatActivity {

    private EditText mEditUsername, mEditPassword;
    private Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEditUsername = (EditText) findViewById(R.id.edtUsername);
        mEditPassword = (EditText) findViewById(R.id.edtPassword);
        mLogInButton = (Button) findViewById(R.id.btnSignIn);

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInUser();
            }
        });

    }

    public void logInUser() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        String username = mEditUsername.getText().toString();
        String password = mEditPassword.getText().toString();

        Account account = db.login(username, password);
        if(account == null) {
            Toast.makeText(this, "Something went wrong, please check username & password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LogIn.this, Profile.class);
            startActivity(intent);
        }
    }
}
