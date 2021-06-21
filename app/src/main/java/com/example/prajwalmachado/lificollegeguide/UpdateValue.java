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

public class UpdateValue extends AppCompatActivity {
    TextView logout;
    Button a,b,c;
    EditText valuee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_value);
        logout=(TextView)findViewById(R.id.textView6);
        a=(Button)findViewById(R.id.button2);
        b=(Button)findViewById(R.id.button3);
        c=(Button)findViewById(R.id.button4);
        valuee=(EditText)findViewById(R.id.editText3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),UserLogin.class);
                startActivity(i);
            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=valuee.getText().toString();
                String idd="A";
                DownloadWebPageTask task = new DownloadWebPageTask();
                task.execute(new String[] {"http://project1.tk/LifiS/upd.php?val="+value+"&number="+idd});
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=valuee.getText().toString();
                String idd="B";
                DownloadWebPageTask task = new DownloadWebPageTask();
                task.execute(new String[] {"http://project1.tk/LifiS/upd.php?val="+value+"&number="+idd});
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=valuee.getText().toString();
                String idd="C";
                DownloadWebPageTask task = new DownloadWebPageTask();
                task.execute(new String[] {"http://project1.tk/LifiS/upd.php?val="+value+"&number="+idd});
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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            if (result.contains("success")) {
                Toast.makeText(getApplicationContext(),  " Information updated successful", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),UpdateValue.class);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(), " Unsuccessful", Toast.LENGTH_SHORT).show();

            }

        }
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
