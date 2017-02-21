package com.quantumtopia.krauser.loltips;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Krauser on 2/20/2017.
 */
class ServerRequest extends AsyncTask<String, String, String>
{
    public boolean done = false;

    @Override
    protected String doInBackground(String... uri)
    {
        try {
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

            System.out.println("Champion name: [" + name + "]");


            URL icon_url = new URL("http://ddragon.leagueoflegends.com/cdn/5.2.1/img/champion/" + name + ".png");
            System.out.println("Icon URL: [" + icon_url + "]");
            Bitmap bmp = BitmapFactory.decodeStream(icon_url.openConnection().getInputStream());
            //InputStream is = (InputStream) new URL(icon_url).getContent();
            //d = Drawable.createFromStream(is, "src name");

            DataClass dc = DataClass.getInstance();
            dc.d = bmp;
            dc.name = name;

            done = true;

            return "";
        }
        catch (Exception e)
        {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        //Do anything with response..
    }
}
