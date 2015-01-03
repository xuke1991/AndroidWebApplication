package com.amaker.ch13.url;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.amaker.ch13.R;
/**
 * @author KeXu
 * Connect via URL
 */
public class TestURLActivity extends Activity {
	private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_url);
        imageView = (ImageView)findViewById(R.id.ImageView01);
        
        String urlStr = "http://192.168.1.101:8080/Chapter_13_Networking_server/upload/figure.jpg";
        
        try {
			URL url = new URL(urlStr);
			// 1. Directly get input string using URL
			//InputStream in = url.openStream();
			
			// 2. Get URLconnection
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			
			// 3. Using HttpURLConnection for http
			//HttpURLConnection httpConn = (HttpsURLConnection)conn;
			//in = httpConn.getInputStream();
			
			Bitmap bm = BitmapFactory.decodeStream(in);
			
			imageView.setImageBitmap(bm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}