package com.flutterosc.flutterosc;


import android.util.Log;

import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPacket;
import com.illposed.osc.OSCPortOut;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OSCBundleSender {

    final String TAG = "Flutter OSC";

    ArrayList<OSCPacket> packets = new ArrayList<OSCPacket>(3);

    public void bundleSender( String IPAddress, int outGoingPort,  HashMap<String,ArrayList<String>> bundle){

         final  HashMap<String,ArrayList<String>> oscBundle = bundle;
         final String IP  = IPAddress;
         final int Port = outGoingPort;

        Thread OSCNetworking = new Thread() {
            @Override
            public void run() {
                try {
                    for (Map.Entry<String,ArrayList<String>> entry : oscBundle.entrySet()){
                        OSCMessage message = new OSCMessage(entry.getKey());

                        for(int i=0; i < entry.getValue().size(); i++){
                             message.addArgument(entry.getValue().get(i));
                       }
                            message.addArgument(""); // for some reason the
                                                    // JAVA OSC does not like when you add odd number
                                   //          arguments so I added this to make it even all the time
                            packets.add(message);
                         }

                    Date newDate = new Date();
                    long time = newDate.getTime();
                    Integer delayTime = Integer.valueOf("2");
                    time = time + delayTime.longValue();
                    newDate.setTime(time);
                    OSCBundle OSCBundle = new OSCBundle(packets, newDate);

                    try {
                        OSCPortOut port = new OSCPortOut(InetAddress.getByName(IP),Port);
                        port.send(OSCBundle);
                        packets.clear();
                        port.close();
                    } catch (Exception e) {
                        Log.e(TAG, "Can't send bundle");
                    }

                } catch (Exception e){
                    Log.e(TAG, "Thread Error " + e.toString());
                }
                super.run();
                }

            };
             OSCNetworking.start();
    }
}
