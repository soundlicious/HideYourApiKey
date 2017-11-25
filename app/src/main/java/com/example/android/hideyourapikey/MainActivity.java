package com.example.android.hideyourapikey;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView apiCode = findViewById(R.id.api_code);
        TextView apiCodeManifest = findViewById(R.id.api_code_manifest);

        String apiCodeString = BuildConfig.CIA_API_KEY;

        String apiCodeManifestString = null;
        if (apiCodeString != null)
            apiCode.setText("My API Key is : " + apiCodeString);

        //Show That the Api Key has been succesfuly saved in Manifest
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo( this.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = ai.metaData;
            if(metaData != null){
                apiCodeManifestString = (String)metaData.get("MANIFEST_API_KEY");
                apiCodeManifest.setText("My Manifest API Key is : " + apiCodeManifestString);
            } else
                Log.i("Main", "MetaData is null");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
