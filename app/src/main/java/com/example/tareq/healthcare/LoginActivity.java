package com.example.tareq.healthcare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";


    EditText etLoginId, etLoginPassword;
    Button btLogin;
    String userType;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        db = new DatabaseHelper(this);

        etLoginId = (EditText) findViewById(R.id.etLoginId);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
        btLogin = (Button) findViewById(R.id.btn_login);


        //  Login button click listener
        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbType1:
                if (checked)
                    userType = "type 1";
                break;
            case R.id.rbType2:
                if (checked)
                    userType = "type 2";
                break;
            case R.id.rbType3:
                if (checked)
                    userType = "type 3";
                break;
            case R.id.rbType4:
                if (checked)
                    userType = "test";
                break;
        }
    }


// ----------   Login Process ------------------

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

        final String userId = etLoginId.getText().toString();
        final String userPassword = etLoginPassword.getText().toString();

        // TODO: Implement your own authentication logic here.


        User user = new User(userId, userPassword, userType);

        db.addAdmin(); // add admin entry in the login table
        boolean isValid = false;
        try {
            isValid = db.isValidUser(user);
        } catch (Exception e) {
            Log.d(TAG, " ==== Validation Problem");


        }


        if (isValid) {

            // On complete call  onLoginSuccess
            onLoginSuccess();
            // onLoginFailed();

            progressDialog.dismiss();
        } else {
            // On incomplete call  onLoginFailed
            onLoginFailed();
            progressDialog.dismiss();
        }






  /*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
//------------------------------------------

                        Toast.makeText(getApplicationContext(), userType +"    "+userId +"  "+ userPassword +"  ",Toast.LENGTH_SHORT).show();

                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);  */
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btLogin.setEnabled(true);
//        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//        startActivity(intent);
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

        if (userType == null) {
            showCustomToast("লগইন টাইপ সিলেক্ট করুন ");
           // Toast.makeText(this, "লগইন টাইপ সিলেক্ট করুন ", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }



    public void showCustomToast(String text){
        LayoutInflater inflater = getLayoutInflater();
        View view= inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView textView = (TextView) view.findViewById(R.id.tvCustomToastText);
        textView.setText(text);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}
