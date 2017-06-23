package blog.appitude.util;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    static final String SERVER_ADDRESS_STRING =  "192.168.2.43";
    private static final int SERVER_PORT = 4445;

    private AsyncTask<Void, Void, Void> async_client;
    public String Message;

    @SuppressLint("NewApi")
    public void sendData() {
            async_client = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    DatagramSocket ds = null;

                    try {
                        ds = new DatagramSocket();
                        DatagramPacket dp;
                        InetAddress SERVER_ADDRESS = InetAddress.getByName(SERVER_ADDRESS_STRING);
                        dp = new DatagramPacket(Message.getBytes(), Message.length(), SERVER_ADDRESS, SERVER_PORT);
                        ds.setBroadcast(true);
                        ds.send(dp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (ds != null) {
                            ds.close();
                        }
                    }
                    return null;
                }


                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                }
            };


        if (Build.VERSION.SDK_INT >= 11)
            async_client.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async_client.execute();
    }
}