package com.example.prajwalmachado.lificollegeguide;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);
        Button lifii = (Button) findViewById(R.id.button6);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                if(email.equals("admin@gmail.com")&&password.equals("admin")) {
                    Toast.makeText(getApplicationContext(),"Welcome Admin",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), UpdateValue.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_LONG).show();
                }
            }
        });
        lifii.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), " Exit", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}

