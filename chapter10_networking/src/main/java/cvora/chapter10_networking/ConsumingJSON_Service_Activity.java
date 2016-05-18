package cvora.chapter10_networking;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;

public class ConsumingJSON_Service_Activity extends AppCompatActivity {

    public String readJSONFeed(String URL) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = null;
        int response = -1;

        java.net.URL url = new URL(URL);
        URLConnection urlConnection = url.openConnection();

        if(!(urlConnection instanceof HttpURLConnection)){
            throw new IOException("Not an HTTP Connection");
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            response = httpURLConnection.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK){
                in = httpURLConnection.getInputStream();
            }
        }catch (Exception ex){
            Log.d("HttpNetworking",ex.getLocalizedMessage());
            throw new IOException("Error Connecting");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private String DownloadText(String URL) {
        String str = "";
        try{
            str = readJSONFeed(URL);
        }catch (IOException ex){
            Log.d("JSON_Service_Activity",ex.getLocalizedMessage());
        }
        return str;
    }

        private class ReadJSONTaskFeed extends AsyncTask<String,Void,String> {
        protected String doInBackground(String... urls){
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONArray jsonArray = new JSONArray(result);
                Log.i("JSON","Number of surveys in feed: " + jsonArray.length());
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Toast.makeText(getBaseContext(),jsonObject.getString("appeId") + " - " + jsonObject.getString("inputTime"),Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consuming_json__service_);
        new ReadJSONTaskFeed().execute("http://extjs.org.cn/extjs/examples/grid/survey.html");
    }
}
