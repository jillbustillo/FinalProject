package com.example.jillbustillo.bustillofinalproj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button1;
    EditText editext1, editext2;
    TextView txtview;
    Button show;
    DatabaseAdapter DatabaseAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button);
        editext1=(EditText)findViewById(R.id.editText);
        editext2=(EditText)findViewById(R.id.editText2);
        txtview=(TextView)findViewById(R.id.textView3);
        show = (Button)findViewById(R.id.button3);

        DatabaseAdapter = new DatabaseAdapter(this);
        DatabaseAdapter = DatabaseAdapter.open();


        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**if ((!isValidPassword(editext2.getText().toString())) && (!isValidEmail(editext1.getText().toString()))) {
                 Toast.makeText(MainActivity.this, "Invalid Email and Password", Toast.LENGTH_LONG).show();
                 } else if (!isValidEmail(editext1.getText().toString())) {
                 Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                 } else if (!isValidPassword(editext2.getText().toString())) {
                 Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                 } else {**/
                String email = editext1.getText().toString();
                String pword = editext2.getText().toString();
                String uname = editext1.getText().toString();
                String savePassword = DatabaseAdapter.getSinlgeEntry(email);
                String savePassword1 = DatabaseAdapter.getUsername(uname);

                /** if (pword.equals(savePassword)) {
                 Toast.makeText(MainActivity.this, email + " has logged in. \n Password: " + pword, Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(MainActivity.this, BlankHomePage.class);
                 startActivity(intent);


                 }
                 else{
                 Toast.makeText(MainActivity.this, "invalid email or password ", Toast.LENGTH_LONG).show();
                 }**/
                if (pword.equals(savePassword1) | pword.equals(savePassword)) {
                    Toast.makeText(MainActivity.this, uname + " has logged in. \n Password: " + pword, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, OnTouchActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "invalid email or password ", Toast.LENGTH_LONG).show();
                }
            }

        });

        txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }

        });

        show.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        final int cursor = editext2.getSelectionStart();
                                        int event = motionEvent.getAction();
                                        /** if (event == MotionEvent.ACTION_DOWN){
                                         editext2.setTransformationMethod(null);
                                         }
                                         else if (event == MotionEvent.ACTION_UP){
                                         editext2.setTransformationMethod(new PasswordTransformationMethod());
                                         }

                                         return false; **/

                                        switch (event) {
                                            case MotionEvent.ACTION_DOWN:
                                                editext2.setTransformationMethod(null);
                                                editext2.setSelection(cursor);
                                                break;
                                            case MotionEvent.ACTION_UP:
                                                editext2.setTransformationMethod(new PasswordTransformationMethod());
                                                editext2.setSelection(cursor);
                                                break;
                                            /**case MotionEvent.ACTION_CANCEL:
                                             editext2.setTransformationMethod(new PasswordTransformationMethod());**/
                                        }

                                        return false;
                                    }
                                }
        );
    }


    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }



}




