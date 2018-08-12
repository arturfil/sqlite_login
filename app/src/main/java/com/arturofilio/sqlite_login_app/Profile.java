package com.arturofilio.sqlite_login_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arturofilio.sqlite_login_app.models.Account;

public class Profile extends AppCompatActivity {

    private static final String TAG = "Profile";

    private TextView mTextBgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mTextBgt = (TextView) findViewById(R.id.textBgt);

        Intent intent = getIntent();

        Account account = (Account) intent.getSerializableExtra("account");

        double budget = account.getBudget();

        mTextBgt.setText(String.valueOf(budget));
    }
}
