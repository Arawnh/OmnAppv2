package ie.wit.omnappv2.main;

import android.app.Application;
import android.util.Log;

import ie.wit.omnappv2.database.DBHelper;

/**
 * Created by Pav on 09/03/2016.
 */
public class OmnApplication extends Application
{
    public int userBMI;
    public int totalCalories;
    public int weight;
    public int height;
    public String userName;
    public String useCheck;

    public DBHelper myDB; // = new DBHelper(this);



    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
        myDB = new DBHelper(this);



    }


}
