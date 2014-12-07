package com.example.opelkantine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class PDFDownloaderTask extends AsyncTask<String, String, String>{

			    @Override
		    protected String doInBackground(String... sUrl) {
		        InputStream input = null;
		        OutputStream output = null;
		        HttpURLConnection connection = null;
		        try {
		            URL url = new URL(sUrl[0]);
		            connection = (HttpURLConnection) url.openConnection();
		            connection.connect();

		            // expect HTTP 200 OK, so we don't mistakenly save error report
		            // instead of the file
		            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
		                return "Server returned HTTP " + connection.getResponseCode()
		                        + " " + connection.getResponseMessage();
		            }

		            // this will be useful to display download percentage
		            // might be -1: server did not report the length
		            int fileLength = connection.getContentLength();
		            System.out.println("File Länge:"+fileLength);
		            // download the file
		            input = connection.getInputStream();
		            
		            //create folder if it does not exist
		            File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/OpelKantine");
		            boolean success = true;
		            if (!folder.exists()) {
		            success = folder.mkdir();
		            }
		            if (success) {
		            Log.d("ordner", "erfolgreich angelegt");
		            } else {
		            Log.d("ordner", "nicht erfolgreich angelegt");
		            } 
		            
		            //now download the current file
		            output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/OpelKantine/aktuelle_Opel_Speisekarte.pdf");

		            byte data[] = new byte[4096];
		            long total = 0;
		            int count;
		            while ((count = input.read(data)) != -1) {
		                // allow canceling with back button
		                if (isCancelled()) {
		                    input.close();
		                    return null;
		                }
		                total += count;
		                // publishing the progress....
		                if (fileLength > 0) // only if total length is known
		                    //publishProgress((int) (total * 100 / fileLength));
		                output.write(data, 0, count);
		            }
		        }
		        
		        
		        
		        
		        
		        
		        
		        catch (Exception e) {
		            return e.toString();
		        } finally {
		            try {
		                if (output != null)
		                    output.close();
		                if (input != null)
		                    input.close();
		            } catch (IOException ignored) {
		            }

		            if (connection != null)
		                connection.disconnect();
		        }
		        return "Successfull downloaded";
		    }
	
    protected void onProgressUpdate(String... progress) {
      
    }

    protected void onPostExecute(String result) {
    	Log.d("Result des Async Task", result);
    	
    	//now get the data out of the pdf:

    	
    	
    }

}
