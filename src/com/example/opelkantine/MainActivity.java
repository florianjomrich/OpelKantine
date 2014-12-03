package com.example.opelkantine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import com.example.opelkantine.R;



import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
	
	String URL = "http://opel.compass-group.de/ruesselsheim/";
	String pdfURL ="http://opel.compass-group.de/ruesselsheim/45%20Speiseplan_I%20Bau_KW45_.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
    	super.onResume();
    	System.out.println("Hallo Welt");
    	
    	
    	PDFDownloaderTask pdfDownloader = new PDFDownloaderTask();
    	pdfDownloader.execute(pdfURL);
    
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
