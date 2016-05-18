package cvora.chapter10_networking;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Admin on 5/17/2016.
 */
public class CommsThread extends Thread{

    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public CommsThread(Socket sock){
        socket = sock;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try{

            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        }catch (IOException e){
            Log.d("SocketChat",e.getLocalizedMessage());
        }
        inputStream = tmpIn;
        outputStream = tmpOut;

    }

    public void run(){

        byte[] buffer = new byte[1024];
        int bytes;

        while(true){
            try{
                bytes = inputStream.read(buffer);
                // update the main activity UI
                SocketsActivity.UIupdater.obtainMessage(0,bytes,-1,buffer).sendToTarget();
            }catch (IOException e){
                break;
            }
        }
    }

    public void write(byte[] bytes){
        try{
            outputStream.write(bytes);
        }catch (IOException e){

        }
    }

    public void cancel(){
        try{
            socket.close();
        }catch (IOException e){

        }
    }


}