package com.amaker.ch13;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amaker.ch13.http.LoginActivity;
import com.amaker.ch13.socket.TestSocketActivity;
import com.amaker.ch13.url.TestURLActivity;
import com.amaker.ch13.webservice.TestWebServiceActivity;
import com.amaker.ch13.webservice.WeatherActivity;
import com.amaker.ch13.webview.TestWebViewActivity;

public class MainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	// Menu
        String[] items = {" Socket","URL¡¢UrlConnection"," HTTP"," Web Service","Weather Forecast","Test WebView"};
		// ListView
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items));
		getListView().setTextFilterEnabled(true);
    }
    
 
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this,TestSocketActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(MainActivity.this,TestURLActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this,LoginActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(MainActivity.this,TestWebServiceActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(MainActivity.this,WeatherActivity.class);
			startActivity(intent);
			break;
		case 5:
			intent = new Intent(MainActivity.this,TestWebViewActivity.class);
			startActivity(intent);
			break;
		}
	}
}