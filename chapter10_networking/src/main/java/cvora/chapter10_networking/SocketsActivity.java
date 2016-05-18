package cvora.chapter10_networking;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketsActivity extends AppCompatActivity {

    static final String NICKNAME = "Chintan Vora";
    InetAddress serverAddress;
    Socket socket;

    static TextView txtMessagesReceived;
    EditText txtMessage;

    CommsThread commsThread;

    static Handler UIupdater = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            int numOfBytesReceived = msg.arg1;
            byte[] buffer = (byte[])msg.obj;

            String strReceived = new String(buffer);
            strReceived = strReceived.substring(0,numOfBytesReceived);

            txtMessagesReceived.setText(txtMessagesReceived.getText().toString() + strReceived);
        }
    };

    private class CreateCommThreadTask extends AsyncTask<Void, Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                serverAddress = InetAddress.getByName("10.0.0.233");
                socket = new Socket(serverAddress,500);
                commsThread = new CommsThread(socket);
                commsThread.start();
                sendToServer(NICKNAME);
                }catch (UnknownHostException e){
                Log.d("Sockets",e.getLocalizedMessage());
            }catch (IOException e){
                Log.d("Sockets",e.getLocalizedMessage());
            }
            return null;
        }
    }

    private class WriteToServerTask extends AsyncTask<byte[],Void,Void>{

        @Override
        protected Void doInBackground(byte[]... params) {
            commsThread.write(params[0]);
            return null;
        }

    }

    private class CloseSocketTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                socket.close();
            }catch (IOException e){
                Log.d("Sockets",e.getLocalizedMessage());
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sockets);

        txtMessage = (EditText)findViewById(R.id.txtMessage);
        txtMessagesReceived = (TextView)findViewById(R.id.txtMessagesReceived);
    }

    public void onClickSend(View view){
        sendToServer(txtMessage.getText().toString());
    }

    private void sendToServer(String message){
        byte[] byteArray = message.getBytes();
        new WriteToServerTask().execute(byteArray);
    }

    @Override
    public void onResume() {
        super.onResume();
        new CreateCommThreadTask().execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        new CloseSocketTask().execute();
    }
}
