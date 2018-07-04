package omi.namnt.requesturl;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    String result[];
    TextView txtHello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHello=findViewById(R.id.txt_hello);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://gateway.fpts.com.vn/G5G/fpts/?s=codename2&c=0&language=1";
                URL yahoo = null;
                try {
                    yahoo = new URL(url);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(yahoo.openStream()));
                    String inputLine;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        stringBuilder.append(inputLine);
                    }
                    result = stringBuilder.toString().split("#");
                    runOnUiThread(new Runnable() {
                        @SuppressLint("ShowToast")
                        @Override
                        public void run() {
                            StringBuilder s= new StringBuilder();
                            for (int i = 1; i < result.length; i+=2) {
                                s.append(result[i]+"\n");
                            }
                            Toast.makeText(MainActivity.this,s, LENGTH_LONG);
                            txtHello.setText(s);
                        }
                    });
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }).start();

    }

}
