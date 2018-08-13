package com.arturofilio.sqlite_login_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.arturofilio.sqlite_login_app.models.Account;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    private static final String TAG = "Profile";

    private TextView mTextBgt, mDayExp, mTextFullName;
    private ListView mListViewPmts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //mTextFullName = (TextView) findViewById(R.id.txtName);
        mTextBgt = (TextView) findViewById(R.id.txtBgt);
        mDayExp = (TextView) findViewById(R.id.textDayExp);

        Intent intent = getIntent();

        Account account = (Account) intent.getSerializableExtra("account");

        double budget = account.getBudget();

        Calendar cal = Calendar.getInstance();
        double dayS = cal.get(Calendar.DAY_OF_MONTH);
        double dayEnd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        double remaining = dayEnd - dayS;

        DecimalFormat df = new DecimalFormat("0.0#");

        String dayExp = df.format(budget / remaining);

        mTextBgt.setText(String.valueOf("$" + budget));
        mDayExp.setText(String.valueOf("$" + dayExp));

    }

}
