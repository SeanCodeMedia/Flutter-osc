package com.flutterosc.flutterosc;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import io.flutter.plugin.common.EventChannel;
import com.illposed.osc.OSCListener;


public class ListenerOSC  {

    OSCPortIn receiver;
    final String TAG = "Flutter OSC";
    boolean run = false;
    String addressSelector = "";


    public ListenerOSC(int InComingPort, String addressSelector) throws SocketException {
          this.addressSelector = addressSelector;
          receiver = new OSCPortIn(InComingPort);
     }

    public void JavaOSCListener(final EventChannel.EventSink emitter) throws SocketException {

        OSCListener listener = new OSCListener() {
            @Override
            public void acceptMessage(java.util.Date time, final OSCMessage oscMessage) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        emitter.success(oscMessage.getAddress());
                    }
                });

            }
        };
        receiver.addListener(addressSelector, listener);
        receiver.startListening();


        if (receiver.isListening()) {
            Log.d(TAG, "Listening");
        } else {
            Log.d(TAG, " Not Listening");
        }

    }


    void stopListening(){
         receiver.stopListening();
    }

    }

