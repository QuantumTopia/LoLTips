package com.quantumtopia.krauser.loltips;

import android.graphics.drawable.Drawable;
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

    public void getVarus(View v) throws Exception
    {
        String url = "https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion/110?champData=all&api_key=RGAPI-7b691aba-e27f-47d9-9d3c-930cb91c4a38";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        JSONObject reader = new JSONObject(response.toString());
        JSONArray champ = reader.getJSONArray("recommended");
        JSONObject c = champ.getJSONObject(1);
        String name = c.getString("champion");

        String icon_url = "http://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/" + name + ".png";
        Drawable d;
        try
        {
            InputStream is = (InputStream) new URL(icon_url).getContent();
            d = Drawable.createFromStream(is, "src name");
        }
        catch (Exception e)
        {
            return;
        }

        TextView tv = (TextView) findViewById(R.id.champion_name_tv);
        ImageView iv = (ImageView) findViewById(R.id.champion_name_iv);

        tv.setText(name);
        iv.setImageDrawable(d);
    }
}
