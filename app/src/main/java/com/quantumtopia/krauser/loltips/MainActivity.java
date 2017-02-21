package com.quantumtopia.krauser.loltips;

import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //what the fuck man
        //same thanks
    }

    public void getVarus(View v)
    {
        ServerRequest sr = new ServerRequest();
        sr.execute();

        while(!sr.done);

        DataClass dc = DataClass.getInstance();

        TextView tv = (TextView) findViewById(R.id.champion_name_tv);
        ImageView iv = (ImageView) findViewById(R.id.champion_name_iv);
        tv.setText(dc.name);
        iv.setImageBitmap(dc.d);
    }
}
