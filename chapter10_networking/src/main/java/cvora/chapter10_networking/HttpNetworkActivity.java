package cvora.chapter10_networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class HttpNetworkActivity extends AppCompatActivity {

    ImageView imageView;

    private InputStream OpenHttpConnection(String urlString) throws IOException{

        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
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
        return in;
    }

    private Bitmap DownloadImage(String URL){
        Bitmap bitmap = null;
        InputStream in = null;
        try{
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        }catch (IOException ex){
            Log.d("HttpNetworkingActivity",ex.getLocalizedMessage());
        }
        return bitmap;
    }

    private String DownloadText(String URL){
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try{
            in = OpenHttpConnection(URL);
        }catch (IOException ex){
            Log.d("HttpNetworkingActivity",ex.getLocalizedMessage());
            return "";
        }

        InputStreamReader inputStreamReader = new InputStreamReader(in);
        int charRead;
        String str = "";
        char [] inputBuffer  = new char[BUFFER_SIZE];
        try{
            while ((charRead = inputStreamReader.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
        }catch (IOException ex){
            Log.d("HttpNetworkingActivity",ex.getLocalizedMessage());
            return "";
        }
        return str;
    }

    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap>{
        protected Bitmap doInBackground(String... urls){
            return DownloadImage(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView = (ImageView)findViewById(R.id.img);
            imageView.setImageBitmap(bitmap);
        }
    }

    private class DownloadTextTask extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... urls){
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();
        }
    }

    private String WordDefinition(String word){

        InputStream in = null;
        String strDefinition = "";
        try{
            in = OpenHttpConnection("http://services.aonaware.com/DictService/DictService.asmx/Define?word=" + word);
            Document doc = null;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;

            try {
                db = dbf.newDocumentBuilder();
                doc = db.parse(in);
            }catch (ParserConfigurationException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            doc.getDocumentElement().normalize();

            // retrieve all the <Definitions> elements
            NodeList definitionElements = doc.getElementsByTagName("Definition");

            // iterate through each <Definition> elements
            for (int i = 0;i<definitionElements.getLength();i++){
                Node itemNode = definitionElements.item(i);
                if(itemNode.getNodeType() == Node.ELEMENT_NODE){
                    // convert the definition node into an Element
                    Element definitionElement = (Element)itemNode;
                    // get all the <WordDefinition> elements under the <Definition> element
                    NodeList wordDefinitionElements = (definitionElement).getElementsByTagName("WordDefinition");
                    strDefinition = "";
                    for(int j=0;j<wordDefinitionElements.getLength();j++){
                        // convert a <WordDefinition> node into an Element
                        Element wordDefinitionElement = (Element)wordDefinitionElements.item(j);

                        // get all child nodes under the <WordDefinition> element
                        NodeList textNodes = ((Node)wordDefinitionElement).getChildNodes();
                        strDefinition += ((Node)textNodes.item(0)).getNodeValue() + ". \n";
                    }
                }
            }
        }catch (IOException ex){
            Log.d("HttpNetworkingActivity",ex.getLocalizedMessage());
        }
        return strDefinition;
    }

    private class AccessWebServiceTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            return WordDefinition(params[0]);
        }

        protected void onPostExecute(String result){
            Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View v){
        new DownloadTextTask().execute("http://www.google.com");
    }

    public void onGetXmlDataClick(View v){
        new AccessWebServiceTask().execute("apple");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_network);
        new DownloadImageTask().execute("http://www.mayoff.com/5-01cablecarDCP01934.jpg");
    }
}
