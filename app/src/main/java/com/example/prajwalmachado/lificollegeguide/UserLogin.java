package com.example.prajwalmachado.lificollegeguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserLogin extends AppCompatActivity {
    EditText user, pass;
    Button signin;
    TextView reg, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        user = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        signin = findViewById(R.id.button);
        reg = findViewById(R.id.textView11);

        admin = findViewById(R.id.textView12);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mInHome = new Intent(UserLogin.this, LoginActivity.class);
                startActivity(mInHome);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = user.getText().toString();
                String p = pass.getText().toString();
                DownloadWebPageTask taskk = new DownloadWebPageTask();
                taskk.execute(new String[]{"http://project1.tk/LifiS/login.php?name="+u+"&pwd="+p});
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mInHome = new Intent(UserLogin.this, RegisterPage.class);
                startActivity(mInHome);
            }
        });
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                if (result.contains("success")) {
                    Toast.makeText(getApplicationContext(), " Login successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), " Unsuccessful", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
