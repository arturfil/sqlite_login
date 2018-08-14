package com.arturofilio.sqlite_login_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arturofilio.sqlite_login_app.DatabaseHelper.DatabaseHelper;
import com.arturofilio.sqlite_login_app.models.Account;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    private static final String TAG = "Profile";

    private Button mButtonSave, mButtonCancel;
    private EditText mMthBdgt, mUserName, mTextFullName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //mTextFullName = (TextView) findViewById(R.id.txtName);
        mUserName = (EditText) findViewById(R.id.edtUsername);
        mTextFullName = (EditText) findViewById(R.id.edtFullName);
        mMthBdgt = (EditText) findViewById(R.id.edtBgt);

        mButtonSave = (Button) findViewById(R.id.btnSave);
        mButtonCancel = (Button) findViewById(R.id.btnCancel);

        final Intent intent = getIntent();

        final Account account = (Account) intent.getSerializableExtra("account");

        double budget = account.getBudget();

        Calendar cal = Calendar.getInstance();
        double dayS = cal.get(Calendar.DAY_OF_MONTH);
        double dayEnd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        double remaining = dayEnd - dayS;

        DecimalFormat df = new DecimalFormat("0.0#");

        String dayExp = df.format(budget / remaining);

        mUserName.setText(account.getUsername());
        mTextFullName.setText(account.getFullName());
        mMthBdgt.setText(String.valueOf(account.getBudget()));

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Home.class);
                startActivity(intent);
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    Account currentAccount = db.findAccount(account.getId());
                    boolean accountcheck = db.checkUsername(mUserName.getText().toString());
                    if(!accountcheck) {
                        currentAccount.setUsername(mUserName.getText().toString());
                        currentAccount.setFullName(mTextFullName.getText().toString());
                        currentAccount.setBudget(Double.parseDouble(mMthBdgt.getText().toString()));

                    }
                    if(db.update(currentAccount)) {
                        Toast.makeText(Profile.this, "Updated account succesfuly!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Profile.this, LogIn.class);
                        intent.putExtra("account", account);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Profile.this,
                                "There was an uknown error, please try again later",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Profile.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}
