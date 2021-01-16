package com.flutterosc.flutterosc;

import android.icu.util.LocaleData;
import android.util.Log;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import java.net.InetAddress;
import java.util.ArrayList;

public class SendOSC {

    final String TAG = "Flutter OSC";

    public void sendOSCMessage(String IPAddress, int outGoingPort, String OSCAddress,  ArrayList<String> OSCMessage) {

        Log.d(TAG, "sendOSCMessage: sending message");

        final String IP = IPAddress;
        final int port  = outGoingPort;
        final String address = OSCAddress;
        final ArrayList<String>  message = OSCMessage;


        Thread OSCNetworking = new Thread() {
            @Override
            public void run() {
                try {

                    OSCPortOut oscPortOut = new OSCPortOut(InetAddress.getByName(IP),port);
                    OSCMessage messageObject = new OSCMessage();
                    messageObject.setAddress(address);

                    for(int i=0; i < message.size(); i++){
                        messageObject.addArgument(message.get(i));
                    }

                    try {

                        oscPortOut.send(messageObject);
                        oscPortOut.close();
                        Log.d(TAG, IP + " " + port);
                        Log.d(TAG, address + " " + message);

                    } catch (Exception e) {

                        Log.e(TAG, " Error osc message was not sent " + e.toString());

                    }
                } catch (Exception e) {

                    Log.e(TAG, "Error " +  e.toString());
                }

                super.run();
            }
        };

        OSCNetworking.start();

    }

}

