package com.example.tarikul.bashabhara.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarikul.bashabhara.R;
import com.example.tarikul.bashabhara.database.SignUpDB;

import static com.example.tarikul.bashabhara.utill.SaveValuePreference.saveBoleanValueSharedPreferences;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName,edtPass;
    SignUpDB signUpDB;
    String userName = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();


    }
    public void signup1(View view )
    {
        Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
        startActivity(intent);
        ActivityCompat.finishAffinity(LoginActivity.this);
    }
    public void btnLogin(View view )
    {
        userName = edtUserName.getText().toString();
        password = edtPass.getText().toString();


        if(signUpDB.checkUser(userName,password)){
            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
            startActivity(intent);
            ActivityCompat.finishAffinity(LoginActivity.this);
            saveBoleanValueSharedPreferences("check_first_time",true,LoginActivity.this);
        }else {
            Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
        }

    }


    public void initViews(){
        edtUserName = (EditText)findViewById(R.id.edt_user);
        edtPass = (EditText)findViewById(R.id.edt_pass);
        signUpDB = new SignUpDB(LoginActivity.this);
        signUpDB.open();

    }

}
