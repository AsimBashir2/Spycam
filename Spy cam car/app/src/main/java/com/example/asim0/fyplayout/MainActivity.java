package com.example.asim0.fyplayout;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    EditText ip_address;
   Button btn_submit;
    String validation,sending;
    String match = "21567", b, c, d, e;
    String f = "true";
    String g = "false";
    boolean z;

    String[] a;

    //int z = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ip_layout);
        btn_submit = findViewById(R.id.btn_submit);
        ip_address = findViewById(R.id.ip_address);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
                //Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                if (z == true) {
                    sending = ip_address.getText().toString();
                    Intent i = new Intent(MainActivity.this, Carcontrol.class);
                    i.putExtra("Ipaddress",sending);
                    startActivity(i);
                } else {
                    //  Toast.makeText(MainActivity.this, "not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Boolean Validation() {
//         final Pattern IP_ADDRESS
//                = Pattern.compile(
//                "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
//                        + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
//                        + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
//                        + "|[1-9][0-9]|[0-9]))");


        validation = ip_address.getText().toString();
        if (validation.contains(":")) {
            a = validation.split(":");

            if(a.length<=1)
            {
                b=a[0];
                c=" ";

            }
            else
            {
                b=a[0];
                c=a[1];

            }

                if(Patterns.IP_ADDRESS.matcher(b).matches())
                {

                    if(c==" ")
                    {
                        Toast.makeText(this, "Please enter port number", Toast.LENGTH_SHORT).show();
                    }

                    else if(!c.equals(match))
                    {
                        Toast.makeText(this, "enter valid Port number 21567", Toast.LENGTH_SHORT).show();
                        z=false;
                    }
                    else if(validation.isEmpty())
                    {
                        Toast.makeText(this, "enter ip address", Toast.LENGTH_SHORT).show();
                        z=false;
                    }
                    else if(validation.length()>21)
                    {
                        Toast.makeText(this, "Enter valid ip address", Toast.LENGTH_SHORT).show();
                        z=false;
                    }
                    else
                    {
                        z=true;
                    }

                }
                else
                {
                    Toast.makeText(this, "Enter valid ip address", Toast.LENGTH_SHORT).show();
                }
        }
        else
        {
            Toast.makeText(this, "Enter both ip address and port number correctly", Toast.LENGTH_SHORT).show();
            z=false;
        }

/////////////////////////////////////////////////////////////////////////////////////////////////////

//        else{
//            z=false;
//            Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();
//        }

//        Matcher matcher = IP_ADDRESS.matcher(b);
            //    if (matcher.matches()) {
            // ip is correct
            //      Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            // }
            //Toast.makeText(MainActivity.this, ""+c, Toast.LENGTH_SHORT).show();
//        if(Patterns.IP_ADDRESS.matcher(b).matches()){
//            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
//        }
//        else if(!c.equals(match))
//        {
//
//
//
//            Toast.makeText(MainActivity.this, "enter valid port number 21567", Toast.LENGTH_SHORT).show();
//            z=false;
//
//        }
//        else if (validation.isEmpty()) {
//            Toast.makeText(MainActivity.this, "Please enter the ip address", Toast.LENGTH_SHORT).show();
//            z=false;
//        } else if (validation.length() > 21) {
//            Toast.makeText(MainActivity.this, "Please enter a valid ip address", Toast.LENGTH_SHORT).show();
//            z=false;
//        }
//
//        else {
//            z=false;
//            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
//
////            Intent i = new Intent(MainActivity.this, Carcontrol.class);
////            startActivity(i);
//        }
            return z;
        }
    }




//        //////////////////////////////////////////////////////////

        //ip_address.setInputType(InputType.TYPE_CLASS_NUMBER);

//        ip_address.addTextChangedListener(ipWatcher);
//
//    }
//
//    private final TextWatcher ipWatcher = new TextWatcher(){
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if(s.length()==0)
//            {
//                Toast.makeText(MainActivity.this, "please enter ip address ", Toast.LENGTH_SHORT).show();
//            }
//            else if(s.length()>=20)
//            {
//                Toast.makeText(getApplicationContext(), "Please enter valid ip address", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    };

