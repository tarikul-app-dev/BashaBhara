package com.example.tarikul.bashabhara.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tarikul.bashabhara.R;
import com.example.tarikul.bashabhara.database.SignUpDB;

public class SignupActivity extends AppCompatActivity {
    EditText edtEmail,edtUserName,edtPassword,edtMobileNum;
    String email = "";
    String userName = "";
    String password = "";
    String mobileNumber = "";
    SignUpDB signUpDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        initViews();


    }
    public void signUp(View view)
    {
            email = edtEmail.getText().toString();
            userName = edtUserName.getText().toString();
            password = edtPassword.getText().toString();
            mobileNumber = edtMobileNum.getText().toString();


            signUpDB.saveInfo(email,userName,password,mobileNumber);
            signUpDB.close();

            edtEmail.setText("");
            edtUserName.setText("");
            edtPassword.setText("");
            edtMobileNum.setText("");


    }
    public void signInWindow(View view)
    {
        Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
        startActivity(intent);
        ActivityCompat.finishAffinity(SignupActivity.this);
    }

    public void initViews(){
        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtUserName = (EditText)findViewById(R.id.edt_user_name);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        edtMobileNum = (EditText)findViewById(R.id.edt_mob);

        signUpDB = new SignUpDB(SignupActivity.this);
        signUpDB.open();

    }

}
