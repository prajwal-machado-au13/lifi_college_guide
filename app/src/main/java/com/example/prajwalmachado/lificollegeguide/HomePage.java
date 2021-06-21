package com.example.prajwalmachado.lificollegeguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomePage extends AppCompatActivity {
Button s1,s2,s3;
TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        s1=findViewById(R.id.button);
        s2=findViewById(R.id.button2);
        s3=findViewById(R.id.button5);
        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView3);
        t3=findViewById(R.id.textView4);


        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    public void run() {
                        // TODO: Your application init goes here.
                        Intent mInHome = new Intent(HomePage.this, HomePage.class);
                        startActivity(mInHome);
                        finish();
                    }
                }, 15000);

        DownloadWebPageTask taskk = new DownloadWebPageTask();
        taskk.execute(new String[]{"http://project1.tk/LifiS/sel.php"});
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
            String bb = result;
            String[] parts = bb.split(",");
            String part1 = parts[0]; // 004
            String part2 = parts[1];
//            try {
//                JSONArray jArray = new JSONArray(result);
//                for (int i = 0; i < jArray.length(); i++) {
//
//                    JSONObject json_data = jArray.getJSONObject(i);
//                    String val=json_data.getString("lifii");
                    if(part1.equals("A"))
                    {
                        s1.setBackgroundResource(R.drawable.bb);
                        t1.setText(part2);
                    }
                    else if(part1.equals("B"))
                    {
                        s2.setBackgroundResource(R.drawable.bb);
                        t2.setText(part2);
                    }
                    else if(part1.equals("C"))
                    {
                        s3.setBackgroundResource(R.drawable.bb);
                        t3.setText(part2);
                    }

                }


//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }

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
