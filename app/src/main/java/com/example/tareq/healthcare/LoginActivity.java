package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";


    EditText etLoginId, etLoginPassword;
    Button btLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginId= (EditText) findViewById(R.id.etLoginId);
        etLoginPassword= (EditText) findViewById(R.id.etLoginPassword);
        btLogin = (Button) findViewById(R.id.btn_login);


        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("অপেক্ষা করুন...");
        progressDialog.show();

        String loginId = etLoginId.getText().toString();
        String loginPassword =etLoginPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }




    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btLogin.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "লগ-ইন ব্যর্থ হয়েছে", Toast.LENGTH_LONG).show();

        btLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String loginId = etLoginId.getText().toString();
        String loginPassword = etLoginPassword.getText().toString();

        if (loginId.isEmpty()) {
            etLoginId.setError("বৈধ লগ-ইন আইডি প্রবেশ করুন");
            valid = false;
        } else {
            etLoginId.setError(null);
        }

        if (loginPassword.isEmpty() || loginPassword.length() < 4 || loginPassword.length() > 10) {
            etLoginPassword.setError("৪ থেকে ১০ অক্ষরের মধ্যে পাসওয়ার্ড প্রবেশ করুন");
            valid = false;
        } else {
           etLoginPassword.setError(null);
        }

        return valid;
    }
}
