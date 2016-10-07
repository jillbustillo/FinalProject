package com.example.jillbustillo.bustillofinalproj;

/**
 * Created by Jill Bustillo on 10/4/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class SignUp extends AppCompatActivity {
    EditText fnamesu, lnamesu, unamesu, emailsu, passu, conpassu;
    Button buttonsu;
    DatabaseAdapter DatabaseAdapter;

    private Toast popToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        fnamesu = (EditText) findViewById(R.id.fname_txt);
        lnamesu = (EditText) findViewById(R.id.lname_txt);
        unamesu = (EditText) findViewById(R.id.uname_txt);
        emailsu = (EditText) findViewById(R.id.email_txt);
        passu = (EditText) findViewById(R.id.pword_txt);
        conpassu = (EditText) findViewById(R.id.cpword_txt);
        buttonsu = (Button) findViewById(R.id.signup_btn);

        DatabaseAdapter = new DatabaseAdapter(this);
        DatabaseAdapter = DatabaseAdapter.open();

        assert buttonsu != null;
        buttonsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailsu.getText().toString();
                String uname = unamesu.getText().toString();
                String savePassword = DatabaseAdapter.getEmailforsignup(email);
                String savePassword1 = DatabaseAdapter.getUsernameforsignup(uname);
                /**if ((isValidPassword(passu.getText().toString()))!=(isValidConPassword(conpassu.getText().toString())))  {
                 Toast.makeText(SignUp.this, "Password do not match", Toast.LENGTH_LONG).show(); } **/
                if (!isValidEmail(emailsu.getText().toString())) {
                    Toast.makeText(SignUp.this, "Invalid Email", Toast.LENGTH_LONG).show();
                } else if (!isValidPassword(passu.getText().toString())) {
                    Toast.makeText(SignUp.this, "Password Length needs to be at least 8 characters", Toast.LENGTH_LONG).show();
                } else if (!passu.getText().toString().equals(conpassu.getText().toString())) {
                    Toast.makeText(SignUp.this, "Password does not match", Toast.LENGTH_LONG).show();
                } else if (!isValidFname(fnamesu.getText().toString())) {
                    Toast.makeText(SignUp.this, "Invalid Firstname", Toast.LENGTH_LONG).show();
                } else if (!isValidLname(lnamesu.getText().toString())) {
                    Toast.makeText(SignUp.this, "Invalid Lastname", Toast.LENGTH_LONG).show();
                } else if (uname.equals(savePassword1) | email.equals(savePassword)) {
                    Toast.makeText(SignUp.this, "Username or Email already exists", Toast.LENGTH_LONG).show();
                } else /**if(passu.getText().toString().equals(conpassu.getText().toString()))**/ {
                    DatabaseAdapter.insertEntry(fnamesu.getText().toString(), lnamesu.getText().toString(), unamesu.getText().toString(), emailsu.getText().toString(), passu.getText().toString());
                    popToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_SHORT);
                    popToast.setText("Account Successfully Created ");
                    popToast.show();

                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;

    }


    private boolean isValidFname(String fname) {

        //String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String FNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(FNAME_PATTERN);
        Matcher matcher = pattern.matcher(fname);
        return matcher.matches();
    }

    private boolean isValidLname(String lname) {

        //String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String LNAME_PATTERN = "^([A-Za-z] *)+$";
        Pattern pattern = Pattern.compile(LNAME_PATTERN);
        Matcher matcher = pattern.matcher(lname);
        return matcher.matches();
    }


}