package ie.wit.omnappv2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ie.wit.omnappv2.main.OmnApplication;

/**
 * Created by Pav on 08/03/2016.
 */
public class BaseActivity extends AppCompatActivity {


    public OmnApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (OmnApplication) getApplication();

    }



}
