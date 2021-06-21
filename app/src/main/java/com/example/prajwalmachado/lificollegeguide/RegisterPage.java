package com.example.prajwalmachado.lificollegeguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RegisterPage extends AppCompatActivity {
EditText n,e,p,m;
Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        n = findViewById(R.id.editText4);
        e = findViewById(R.id.editText5);
        p = findViewById(R.id.editText7);
        m = findViewById(R.id.editText8);
        reg=findViewById(R.id.button8);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nn = n.getText().toString();
                String ee = e.getText().toString();
                String pp = p.getText().toString();
                String mm= m.getText().toString();
                DownloadWebPageTask taskk = new DownloadWebPageTask();
                taskk.execute(new String[]{"http://project1.tk/LifiS/reg.php?na="+nn+"&em="+ee+"&pa="+pp+"&mo="+mm});
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
                if (result.contains("Already")) {
                    Toast.makeText(getApplicationContext(), " Already exist", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(i);
                }
                else if (result.contains("success")) {
                    Toast.makeText(getApplicationContext(), " Login successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(), " Unsuccessful", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
