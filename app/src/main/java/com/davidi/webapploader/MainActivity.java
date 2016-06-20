package com.davidi.webapploader;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Dror Davidi, 20-Jun-2016.
 * Main Activity class for the WebAppLoader demo app.
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLaunchBrowser = (Button) findViewById(R.id.btn_launch_browser);
        btnLaunchBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBrowser();
            }
        });
    }

    private void launchBrowser() {
        List<ResolveInfo> browserAppsList = getAllBrowserApps();

        if (browserAppsList != null && browserAppsList.size() > 0) {
            ResolveInfo resolveInfo = browserAppsList.get(0);
            Intent intent = new Intent();
            intent.setClassName(resolveInfo.activityInfo.applicationInfo.packageName,
                    resolveInfo.activityInfo.name);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    private List<ResolveInfo> getAllBrowserApps() {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mainIntent.addCategory(Intent.CATEGORY_APP_BROWSER);
        return getApplicationContext().getPackageManager().queryIntentActivities(mainIntent, 0);
    }
}
