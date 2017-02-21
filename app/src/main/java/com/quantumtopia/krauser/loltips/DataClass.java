package com.quantumtopia.krauser.loltips;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Krauser on 2/20/2017.
 */
public class DataClass
{
    private static DataClass instance = null;

    public Bitmap d;
    public String name;

    protected DataClass()
    {

    }

    public static DataClass getInstance()
    {
        if(instance == null)
            instance = new DataClass();

        return instance;
    }
}
