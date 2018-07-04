package com.example.asim0.fyplayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.Window;

/**
 * Created by asim0 on 5/12/2018.
 */

public class splash extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.land_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent i= new Intent(splash.this,MainActivity.class);
                splash.this.finish();
                startActivity(i);
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
