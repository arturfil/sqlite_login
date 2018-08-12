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

    private EditText mEdtUsername, mEdtPassword, mEdtPassword2, mEdtFullName, mMonthBgt;
    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);
        mEdtPassword2 = (EditText) findViewById(R.id.edtConfirm);
        mEdtFullName = (EditText) findViewById(R.id.edtFullName);
        mMonthBgt = (EditText) findViewById(R.id.edtBgt);
        mSignupButton = (Button) findViewById(R.id.btnSignUp);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
    }

    public void registerAccount() {

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Account account = new Account();
        account.setUsername(mEdtUsername.getText().toString());
        account.setPassword(mEdtPassword.getText().toString());
        account.setFullName(mEdtFullName.getText().toString());
        
        try {
            account.setBudget(Double.parseDouble(mMonthBgt.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this, "Budget cannot be empty", Toast.LENGTH_SHORT).show();
        }

        String username = mEdtUsername.getText().toString();
        String password = mEdtPassword.getText().toString();
        String password2 = mEdtPassword2.getText().toString();
        String fullName = mEdtFullName.getText().toString();

        String budget = mMonthBgt.toString();

        
        if(username.isEmpty()  ||
            password.isEmpty() ||
            fullName.isEmpty() ||
                budget.isEmpty() ) {
            
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        } else if (!db.checkUsername(username)) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
        } else if (!password2.equals(password)) {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                db.create(account);
                Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
