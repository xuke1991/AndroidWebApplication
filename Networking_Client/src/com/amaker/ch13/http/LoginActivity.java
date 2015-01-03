package com.amaker.ch13.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.amaker.ch13.R;
public class LoginActivity extends Activity {
	/*
	 * Claim the Button and EditText
	 */
	private Button cancelBtn,loginBtn;
	private EditText userEditText,pwdEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http1);
		/*
		 *  Instantiate
		 */
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		loginBtn = (Button)findViewById(R.id.loginButton);
		
		userEditText = (EditText)findViewById(R.id.userEditText);
		pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		/*
		 * Set up the listener
		 */
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					String username = userEditText.getText().toString();
					String pwd = pwdEditText.getText().toString();
					login(username,pwd);
			}
		});
		
		/*
		 * Cancel the listener
		 */
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		

	}
	
	/*
	 * Show the Dialog
	 */
	private void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	/*
	 * Send a Post request 
	 */
	private void login(String username,String password){
		// 1. Using HttpURLConnection 
		/*String urlStr = "http://192.168.1.101:8080/Chapter_13_Networking_server/servlet/LoginServlet?";
		String queryString = "username="+username+"&password="+password;
		urlStr+=queryString;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				
			if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
				InputStream in = conn.getInputStream();
				byte[] b = new byte[in.available()];
				in.read(b);
				String msg = new String(b);
				showDialog(msg);
				in.close();
			}
			conn.disconnect();
		} catch (Exception e) {
			showDialog(e.getMessage());
		}*/
		// 2. Using Apache HTTP client
		String urlStr = "http://192.168.1.101:8080/Chapter_13_Networking_server/servlet/LoginServlet";
		HttpPost request = new HttpPost(urlStr);
		// Encapsulate the parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		try {
			request.setEntity( new UrlEncodedFormEntity(params,HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				String msg = EntityUtils.toString(response.getEntity());
				showDialog(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}