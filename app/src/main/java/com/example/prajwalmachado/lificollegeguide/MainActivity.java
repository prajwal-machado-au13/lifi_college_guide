package com.example.prajwalmachado.lificollegeguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    public void run() {
                        // TODO: Your application init goes here.
                        Intent mInHome = new Intent(MainActivity.this,  UserLogin.class);
                        MainActivity.this.startActivity(mInHome);
                        MainActivity.this.finish();
                    }
                }, 5000);
    }
}
