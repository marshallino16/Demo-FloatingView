package fr.anthonyfernandez.floatingmenu.Manager;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class PInfo {
    public String appname = "";
    public String pname = "";
    public String versionName = "";
    public int versionCode = 0;
    public Drawable icon;
    public void prettyPrint() {
        Log.v("tag", appname + "\t" + pname + "\t" + versionName + "\t" + versionCode);
    }
}