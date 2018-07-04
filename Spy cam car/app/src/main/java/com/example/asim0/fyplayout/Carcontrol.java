package com.example.asim0.fyplayout;

import android.app.Activity;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by asim0 on 5/12/2018.
 */

public class Carcontrol extends Activity {
    WebView web_view;
    Button btn_submit, btn_stop, btn_forward, btn_backward, btn_right, btn_left, btn_CCW, btn_CW;
    Socket myAppSocket = null;
    public static String wifiMoudleIp = "";
    public static int wifiMoudlePort = 0;
    public static String CMD = "0";
    public static int tempa = 0;
    public static boolean mAutoIncrement = false;
    public static boolean mAutoDecrement = false;
    public static Handler repeatUpdateHandler = new Handler();
    public static TextView temp;
    android.support.v7.app.ActionBar actionBar;
    String IpAddress;
    private LinearLayout mlLayoutRequestError = null;
    private Handler mhErrorLayoutHide = null;

    private boolean mbErrorOccured = false;
    private boolean mbReloadPressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.livestream);
        btn_stop = (Button) findViewById(R.id.stop_btn);
        btn_forward = (Button) findViewById(R.id.forward_btn);
        btn_backward = (Button) findViewById(R.id.backward_btn);
        btn_right = (Button) findViewById(R.id.right_btn);
        btn_left = (Button) findViewById(R.id.left_btn);
        btn_CCW = (Button) findViewById(R.id.ccw_btn);
        btn_CW = (Button) findViewById(R.id.cw_btn);


        //////////////////// GET PUT EXTRAS///////////////////////

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        IpAddress = (String) b.get("Ipaddress");
        //Toast.makeText(this, "ipAddress is =" + IpAddress, Toast.LENGTH_SHORT).show();

        ////////////////////// END //////////////////////////////////

        ///////////////////// SETTING UP IP AND PORT FOR LIVE STREAMING//////////

        String Webtemp[];
        Webtemp = IpAddress.split(":");
          String FirstIp = Webtemp[0];
        String WebPort = "8081";
        web_view = (WebView) findViewById(R.id.webView);
        web_view.setWebViewClient(new MyWebViewClient());
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);





        //web_view.setWebChromeClient(getChromeClient());

        web_view.loadUrl("http://" + FirstIp + ":" + WebPort);
       // web_view.loadUrl("http://search.yahoo.com/search?p=android");
        //////////////////////////// END/////////////////////////////////////

        ////////////////// NOW MAKING ACTIONS ON BUTTONS ///////////////////

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                move("forward");

            }
        });


        btn_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move("backward");

            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move("right");
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move("left");

            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("stop");
            }
        });
        btn_CW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move("clockwise");
            }
        });
        btn_CCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move("aclockwise");
            }
        });

        ////////////////// END/////////////////////////
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Picture picture = view.capturePicture();
            Bitmap  b = Bitmap.createBitmap( picture.getWidth(),
                    picture.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas( b );
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File mypath=new File(directory,"profile.jpg");
            Toast.makeText(cw, "assas"+mypath, Toast.LENGTH_SHORT).show();
            picture.draw( c );
            FileOutputStream fos = null;

            try {

              //  fos = new FileOutputStream( "/sdcard/DCIM/new.jpg" );
                fos = new FileOutputStream(mypath);
                if ( fos != null )
                {
                    b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    Log.d("saving","saving");
                    fos.close();
                }
            }
            catch( Exception e )
            {
                    Log.d("no","nhe yah nhe");
            }

//        if (mbErrorOccured == false && mbReloadPressed) {
//            hideErrorLayout();
//            mbReloadPressed = false;
//        }
//
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            mbErrorOccured = true;
            view.loadUrl("NOT CONNECTED");
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast_container));
            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText("Your Internet Connection May not be active for live Streaming");
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_HORIZONTAL, -50, -100);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            super.onReceivedError(view, request, error);

        }


    }

    //////////////////////////// CREATING MOVE FUNCTIONALITY///////////////////

    public void move(String direction) {
        getIpAddress();
        CMD = direction;
        //socket_AsyncTask cmd_increase_servo = new socket_AsyncTask();
        socket_AsyncTask Perform_direction = new socket_AsyncTask();
        //cmd_increase_servo.execute();
        Perform_direction.execute();
    }

    ////////////////////////////END////////////////////////////////////////

    public void getIpAddress() {
        //String IpAddress = textAddress.getText().toString();
        Log.d("MYTEST", "IP String" + IpAddress);
        String temp[] = IpAddress.split(":");
        //  Toast.makeText(getApplicationContext(), "asass" + temp, Toast.LENGTH_SHORT).show();
        wifiMoudleIp = temp[0];
        wifiMoudlePort = Integer.valueOf(temp[1]);
        Log.d("MY TEST", "IP" + wifiMoudleIp);
        Log.d("MY TEST", "IP" + wifiMoudlePort);

    }


    public class socket_AsyncTask extends AsyncTask<Void, Void, Void> {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InetAddress inetAddress = InetAddress.getByName(Carcontrol.wifiMoudleIp);
                socket = new java.net.Socket(inetAddress, Carcontrol.wifiMoudlePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);
                dataOutputStream.close();
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
