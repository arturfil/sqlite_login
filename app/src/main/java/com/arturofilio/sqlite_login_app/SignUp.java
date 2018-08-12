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

public class SignUp extends AppCompatActivity {

    private EditText mEdtUsername, mEdtPassword, mEdtFullName, mMonthBgt;
    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mEdtFullName = (EditText) findViewById(R.id.edtFullName);
        mMonthBgt = (EditText) findViewById(R.id.edtBgt);
        mSignupButton = (Button) findViewById(R.id.btnSignUp);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
                Intent signIn = new Intent(SignUp.this, LogIn.class);
                startActivity(signIn);
            }
        });
    }

    public void registerAccount() {
        try {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            Account account = new Account();
            account.setUsername(mEdtUsername.getText().toString());
            account.setPassword(mEdtPassword.getText().toString());
            account.setFullName(mEdtFullName.getText().toString());
            account.setBudget(Double.parseDouble(mMonthBgt.getText().toString()));
            if(db.create(account)) {
                Toast.makeText(this, "Sing Up Successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Couldn't Sign Up, please try again.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Couldn't Sign Up, please try again.", Toast.LENGTH_SHORT).show();
        }
    }

}
